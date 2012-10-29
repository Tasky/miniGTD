/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.Controller;
import net.miginfocom.swing.MigLayout;
import util.exceptions.ConnectionException;
import views.content.TaskForm;
import views.content.Thought;
import views.content.ThoughtForm;

import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author tim
 */
public class ContentPanel extends JPanel {

    public ContentPanel(Controller controller) {
        super(null);

        setBackground(new Color(186, 208, 244));
        setLayout(new MigLayout("ins 0", "[grow]", "[]"));

        ThoughtForm form = new ThoughtForm();
        form.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        add(form, "span, growx");

        JPanel holder = new JPanel();
        holder.setLayout(new MigLayout("gap 20px", "[grow]", "[]"));
        holder.setOpaque(false);
        try {
            List<models.Thought> thoughts = controller.getAllThoughts();
            for(models.Thought thought : thoughts) {
                holder.add(new Thought(thought), "span, growx");
            }
        } catch (ConnectionException e) {
            // doe niks!
        }
        add(holder, "span, growx");
    }
}
