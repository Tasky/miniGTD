/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.Controller;
import models.Task;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import models.Project;
import views.filter.*;

public class FilterPanel extends JPanel {
    private final Controller controller;

    public FilterPanel(final Controller controller) {
        super(null);
        this.controller = controller;

        setLayout(new MigLayout("ins 0", "[grow]"));
        setBackground(new Color(230, 238, 251));
        updateFilters();
    }

    public void updateFilters() {
        removeAll();
        add(new Header("Verzamelen"), "span, growx");
        add(new Tab("inbox_empty.png", "inbox", controller), "span, growx");
        add(new Header("Focus"), "span, growx");
        add(new Tab("star.png", "today", controller), "span, growx");
        add(new Tab("date_task.png", "next", controller), "span, growx");
//        add(new Tab("date_task.png", "planned", controller), "span, growx");
        add(new Tab("box_open.png", "ever", controller), "span, growx");
        add(new Header("Projecten"), "span, growx");

        java.util.List<Project> projects = controller.getProjects();
        for (final Project p : projects) {
            Tab tab = new Tab("to_do_list.png", "project_"+p.getId(), controller);
            tab.setText(p.getName());
            tab.setToolTipText(p.getNote());

            DropTarget dropTarget = new DropTarget();
            dropTarget.setDefaultActions(DnDConstants.ACTION_MOVE);
            try {
                dropTarget.addDropTargetListener(new DropTargetAdapter() {
                    @Override
                    public void drop(DropTargetDropEvent event) {
                        Object source = event.getSource();
                        if(source instanceof Task) {
                            Task task = (Task) source;
                            task.setProject(p.getName());
                            controller.save(task);
                        } else {
                            event.rejectDrop();
                        }
                    }
                });
            } catch (TooManyListenersException ignored) {

            }
            tab.setDropTarget(dropTarget);

            tab.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent mouseEvent) {
                    if (mouseEvent.isPopupTrigger()) doPop(mouseEvent);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) doPop(e);
                }

                private void doPop(MouseEvent e) {
                    ProjectPopup menu = new ProjectPopup(p, controller);
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            });

            add(tab, "span, growx");
        }
        JLabel toevoegen = new IconLabel("add.png");
        toevoegen.setText("<html><u>Toevoegen</u>");
        toevoegen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Project p = new Project();
                JTextField name = new JTextField();
                JTextArea notes = new JTextArea();
                notes.setRows(3);
                notes.setColumns(40);
                final JComponent[] inputs = new JComponent[] {
                        new JLabel("Naam"),
                        name,
                        new JLabel("Notities"),
                        new JScrollPane(notes)
                };
                int returnal = JOptionPane.showConfirmDialog(null, inputs, "Project aanmaken", JOptionPane.OK_CANCEL_OPTION);
                if (returnal == JOptionPane.YES_OPTION) {
                    p.setName(name.getText());
                    p.setNote(notes.getText());
                    controller.add(p);
                }
            }
        });
        add(toevoegen, "span, growx");

        add(new Header("Archief"), "span, growx");
        add(new Tab("book.png", "history", controller), "span, growx");
        revalidate();
        repaint();
    }
}