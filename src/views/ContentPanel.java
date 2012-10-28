/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.Controller;
import views.content.Thought;
import views.content.CreatePanel;
import java.awt.Color;
import java.util.Stack;
import javax.swing.*;

import static views.LayoutConstants.*;

/**
 *
 * @author tim
 */
public class ContentPanel extends JPanel {
    
    private CreatePanel noteadd;
    
    public ContentPanel(Controller controller) {
        super(null);

        setBackground(new Color(186, 208, 244));

        noteadd = new CreatePanel();
        noteadd.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
        add(noteadd);
        
    }
}
