/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.Controller;
import models.Task;
import net.miginfocom.swing.MigLayout;
import views.content.TaskForm;
import views.content.TaskItem;
import views.content.ThoughtItem;
import views.content.ThoughtForm;

import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author tim
 */
public class ContentPanel extends JPanel {

    private Controller controller;

    public ContentPanel(Controller controller) {
        super(null);
        this.controller = controller;

        setBackground(new Color(186, 208, 244));
        setLayout(new MigLayout("ins 0", "[grow]", "[]"));
    }

    public void showThoughts(List<models.Thought> thoughts) {
        removeAll();

        ThoughtForm form = new ThoughtForm();
        form.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        add(form, "span, growx");

        JPanel holder = new JPanel();
        holder.setLayout(new MigLayout("gap 20px", "[grow]", "[]"));
        holder.setOpaque(false);
        for(models.Thought thought : thoughts) {
            holder.add(new ThoughtItem(thought), "span, growx");
        }
        add(holder, "span, growx");
        revalidate();
    }

    public void showTasks(List<Task> tasks) {
        removeAll();

        TaskForm form = new TaskForm();
        form.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        add(form, "span, growx");

        JPanel holder = new JPanel();
        holder.setLayout(new MigLayout("gap 20px", "[grow]", "[]"));
        holder.setOpaque(false);
        for(models.Task task : tasks) {
            holder.add(new TaskItem(task), "span, growx");
        }
        add(holder, "span, growx");
        revalidate();
    }
}
