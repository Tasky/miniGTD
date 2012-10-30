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

public class ThoughtItem extends JPanel {

    public ThoughtItem(final Controller controller, final models.Thought thought) {
        setOpaque(false);
        setLayout(new MigLayout("", "[grow][]", "[]"));
        // TODO: knopje toevoegen bij aanpassen textarea
        add(new JTextArea(thought.getNotes()), "growx, aligny top");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new MigLayout("ins 0"));
        buttonPanel.setOpaque(false);
        // TODO: knopjes werkend maken
        JButton btnOpslaan = new JButton("Opslaan");
        btnOpslaan.setEnabled(false);
        buttonPanel.add(btnOpslaan, "span, growx");
        JButton makeActionBtn = new JButton("Maak actie");
        makeActionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TaskForm form = new TaskForm(controller, thought);
                int returnal = JOptionPane.showConfirmDialog(null, form, "Actie aanmaken", JOptionPane.OK_CANCEL_OPTION);
                if (returnal == JOptionPane.YES_OPTION) {;
                    Task task = new Task();
                    form.applyToTask(task);
                    controller.saveTask(task);
                }
            }
        });
        JButton removeBtn = new JButton("Weggooien");
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TaskForm form = new TaskForm(controller);
                int returnal = JOptionPane.showConfirmDialog(null, form, "Weet je zeker dat je deze actie wilt weghalen?", JOptionPane.OK_CANCEL_OPTION);
                if (returnal == JOptionPane.YES_OPTION) {;
//                    controller.saveProject(p);
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
