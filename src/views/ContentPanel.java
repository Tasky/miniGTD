/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.Controller;
import models.Task;
import models.Thought;
import net.miginfocom.swing.MigLayout;
import views.content.TaskForm;
import views.content.TaskItem;
import views.content.ThoughtItem;
import views.content.ThoughtForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author tim
 */
public class ContentPanel extends JPanel {

    private Controller controller;
    private JPanel holder;

    public ContentPanel(Controller controller) {
        super(null);
        this.controller = controller;

        setBackground(new Color(186, 208, 244));
        setLayout(new MigLayout("ins 0", "[grow]", "[]"));
    }

    public void showThoughts(List<Thought> thoughts) {
        removeAll();

        ThoughtForm form = new ThoughtForm(controller);
        form.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        add(form, "span, growx");

        holder = new JPanel();
        holder.setLayout(new MigLayout("gap 20px", "[grow]", "[]"));
        holder.setOpaque(false);
        for(models.Thought thought : thoughts) {
            holder.add(new ThoughtItem(controller, thought), "span, growx");
        }
        add(holder, "span, growx");
        revalidate();
        repaint();
    }

    public void updateThoughts(List<Thought> thoughts) {
        holder.removeAll();
        holder.setLayout(new MigLayout("gap 20px", "[grow]", "[]"));
        holder.setOpaque(false);
        for(models.Thought thought : thoughts) {
            holder.add(new ThoughtItem(controller, thought), "span, growx");
        }
        revalidate();
        repaint();
    }

    public void showTasks(List<Task> tasks, Task.Sort currentSort, boolean asc, boolean formVisible) {
        removeAll();

        if (formVisible) {
            TaskForm form = new TaskForm(controller);
            form.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
            add(form, "span, growx");
        }

        JPanel sortPanel = new JPanel();
        sortPanel.setOpaque(false);
        sortPanel.setLayout(new MigLayout("insets 0 n 0 n", "[grow][][][][][]", ""));
        sortPanel.add(new JLabel(""));
        sortPanel.add(new JLabel("Sorteren op:"));

        class Sort {
            public final String name;
            public final Task.Sort sort;
            public Sort(String name, Task.Sort sort) {
                this.name = name;
                this.sort = sort;
            }
        }
        List<Sort> sorts = new ArrayList<Sort>();
        sorts.add(new Sort("Volgorde", Task.Sort.ORDER));
        sorts.add(new Sort("Actie", Task.Sort.TASK));
        sorts.add(new Sort("Einddatum", Task.Sort.ACTIONDATE));
        sorts.add(new Sort("Status", Task.Sort.STATUS));

        for(final Sort sort : sorts) {
            String name = sort.name;
            if(currentSort.equals(sort.sort))
                if(asc)
                    name += " ↓";
                else
                    name += " ↑";
            JButton jButton = new JButton(name);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    controller.sort(sort.sort);
                }
            });
            sortPanel.add(jButton);
        }

        add(sortPanel, "span, growx");

        holder = new JPanel();
        holder.setLayout(new MigLayout("insets 0 n n n, gap 20px", "[grow]", "[]"));
        holder.setOpaque(false);
        for(models.Task task : tasks) {
            holder.add(new TaskItem(controller, task), "span, growx");
        }
        add(holder, "span, growx");
        revalidate();
        repaint();
    }

}
