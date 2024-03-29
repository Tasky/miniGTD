/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.content;

import views.filter.*;
import controller.Controller;
import models.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import models.Task;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author tim
 */
public class TaskPopup extends JPopupMenu {

    public TaskPopup(final JPanel panel, final Task t, final Controller controller) {
        JMenuItem wijzigen = new JMenuItem("Wijzigen");
        wijzigen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                panel.removeAll();
                panel.setLayout(new MigLayout("", "[grow]"));
                panel.add(new TaskForm(controller, t), "grow");
                panel.revalidate();
                panel.repaint();
            }
        });
        add(wijzigen);
        
        JMenuItem verwijderen = new JMenuItem("Verwijderen");
        verwijderen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int returnal = JOptionPane.showConfirmDialog(null, new JLabel("Weet je zeker dat je deze taak wilt verwijderen?"), "Taak verwijderen", JOptionPane.OK_CANCEL_OPTION);
                if (returnal == JOptionPane.YES_OPTION) {
                    controller.remove(t);
                    controller.refreshTasks();
                }
            }
        });
        add(verwijderen);
    }

}