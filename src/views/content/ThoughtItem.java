/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.content;

import controller.Controller;
import models.Task;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThoughtItem extends JPanel {

    public ThoughtItem(final Controller controller, final models.Thought thought) {
        setOpaque(false);
        setLayout(new MigLayout("", "[grow][]", "[]"));

        JTextArea label = new JTextArea(thought.getNotes());
        label.setLineWrap(true);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger()) return;
                removeAll();
                setLayout(new MigLayout("", "[grow]"));
                add(new ThoughtForm(controller, thought), "grow");
                revalidate();
                repaint();
            }
        });
        add(label, "growx, aligny top");

        JButton makeActionBtn = new JButton("Maak actie");
        JButton removeBtn = new JButton("Weggooien");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new MigLayout("ins 0"));
        buttonPanel.setOpaque(false);

        makeActionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TaskForm form = new TaskForm(controller, thought);
                int returnal = JOptionPane.showConfirmDialog(null, form, "Actie aanmaken", JOptionPane.OK_CANCEL_OPTION);
                if (returnal == JOptionPane.YES_OPTION) {;
                    Task task = new Task();
                    form.applyToTask(task);
                    controller.save(task);
                    controller.remove(thought);
                }
            }
        });

        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int returnal = JOptionPane.showConfirmDialog(null, "Weet je zeker dat je dit wilt verwijderen?", "alert", JOptionPane.OK_CANCEL_OPTION);
                if (returnal == JOptionPane.YES_OPTION) {;
                    controller.remove(thought);
                }
            }
        });
        buttonPanel.add(makeActionBtn, "span, growx");
        buttonPanel.add(removeBtn, "span, growx");

        add(buttonPanel);
    }

    @Override
    public void paint(Graphics g2) {
        Graphics2D g = (Graphics2D) g2;
        Dimension originalSize = super.getSize();

        // rounded corners
        g.setColor(Color.white);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRoundRect(0, 0, originalSize.width, originalSize.height, 10, 10);
        // draw the rest
        super.paint(g);
    }
}
