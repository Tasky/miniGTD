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

public class ContentPanel extends JPanel {

    private Controller controller;
    private JPanel holder;
    private JPanel sortPanel;
    private final List<Sort> sorts;

    /**
     * Tuple
     */
    class Sort {
        public final String name;
        public final Task.Sort sort;
        public Sort(String name, Task.Sort sort) {
            this.name = name;
            this.sort = sort;
        }
    }

    public ContentPanel(Controller controller) {
        super(null);
        this.controller = controller;

        setBackground(new Color(186, 208, 244));
        setLayout(new MigLayout("ins 0", "[grow]", "[]"));

        sorts = new ArrayList<Sort>();
        sorts.add(new Sort("Volgorde", Task.Sort.ORDER));
        sorts.add(new Sort("Actie", Task.Sort.TASK));
        sorts.add(new Sort("Einddatum", Task.Sort.ACTIONDATE));
        sorts.add(new Sort("Status", Task.Sort.STATUS));
    }

    public void showThoughts(List<Thought> thoughts) {
        removeAll();

        ThoughtForm form = new ThoughtForm(controller);
        form.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        add(form, "span, growx");

        add(holder = new JPanel(), "span, growx");
        updateThoughts(thoughts);
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

    public void showTasks(List<Task> tasks, final Task.Sort currentSort, final boolean asc, boolean formVisible) {
        removeAll();

        if (formVisible) {
            TaskForm form = new TaskForm(controller);
            form.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
            add(form, "span, growx");
        }

        add(sortPanel = new JPanel(), "span, growx");
        add(holder = new JPanel(), "span, growx");
        updateTasks(tasks, currentSort, asc);
    }

    public void updateTasks(List<Task> tasks, final Task.Sort currentSort, final boolean asc) {
        sortPanel.removeAll();
        sortPanel.setOpaque(false);
        sortPanel.setLayout(new MigLayout("insets 0 n 0 n", "[grow][][][][][]", ""));
        sortPanel.add(new JLabel(""));
        sortPanel.add(new JLabel("Sorteren op:"));

        for(final Sort sort : sorts) {
            String name = sort.name;
            if(currentSort.equals(sort.sort))
                if(asc) name += " ↓";
                else name += " ↑";
            JButton jButton = new JButton(name);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    controller.sort(sort.sort, !sort.sort.equals(currentSort) || !asc);
                }
            });
            sortPanel.add(jButton);
        }
        holder.removeAll();
        holder.setLayout(new MigLayout("insets 0 n n n, gap 20px", "[grow]", "[]"));
        holder.setOpaque(false);
        for(models.Task task : tasks) {
            holder.add(new TaskItem(controller, task), "span, growx");
        }

        revalidate();
        repaint();
    }
}
