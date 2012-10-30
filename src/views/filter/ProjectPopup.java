/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.filter;

import controller.Controller;
import models.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author tim
 */
public class ProjectPopup extends JPopupMenu {

    public ProjectPopup(final Project p, final Controller controller) {
        JMenuItem hernoemen = new JMenuItem("Hernoemen");
        hernoemen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JTextField name = new JTextField(p.getName());
                JTextArea notes = new JTextArea(p.getNote());
                notes.setRows(3);
                notes.setColumns(40);
                final JComponent[] inputs = new JComponent[] {
                        new JLabel("Naam"),
                        name,
                        new JLabel("Notities"),
                        new JScrollPane(notes)
                };
                int returnal = JOptionPane.showConfirmDialog(null, inputs, "Project hernoemen", JOptionPane.OK_CANCEL_OPTION);
                if (returnal == JOptionPane.YES_OPTION) {
                    p.setName(name.getText());
                    p.setNote(notes.getText());
                    controller.save(p);
                }
            }
        });
        add(hernoemen);

        JMenuItem verwijderen = new JMenuItem("Verwijderen");
        verwijderen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int returnal = JOptionPane.showConfirmDialog(null, new JLabel("Weet je zeker dat je dit project wilt verwijderen?"), "Project verwijderen", JOptionPane.OK_CANCEL_OPTION);
                if (returnal == JOptionPane.YES_OPTION) {
//                    controller.removeProject(p);
                }
            }
        });
        add(verwijderen);
    }
}