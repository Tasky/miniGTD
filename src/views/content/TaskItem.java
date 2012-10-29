package views.content;

import controller.Controller;
import models.Task;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Author: nanne
 */
public class TaskItem extends JPanel {
    public TaskItem(final Controller controller, final Task task) {
        setOpaque(false);
        setLayout(new MigLayout("", "[][grow][]"));


        add(new JCheckBox());
        JLabel label = new JLabel(task.getDescription());
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        label.setFont(new Font("Sans-serif", Font.PLAIN, 16));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger()) return;
                removeAll();
//                add(new JLabel("TEST"));
                add(new TaskForm(controller, task));
                revalidate();
                repaint();
            }
        });
        add(label, "growx");

        add(new JLabel(task.getContext()));

        List<String> strings = controller.getStatuses();
        JComboBox statuses = new JComboBox(strings.toArray());
        statuses.setSelectedItem(task.getStatus());
        add(statuses);

        add(new JLabel(task.getActionDate().toString()), "span");
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
