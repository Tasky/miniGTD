/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.Controller;
import net.miginfocom.swing.MigLayout;
import views.content.TaskForm;
import views.content.ThoughtForm;

import java.awt.*;
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
        ThoughtForm noteadd = new ThoughtForm();
        noteadd.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        add(noteadd, "growx");
        
    }
}
