/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.content.Thought;
import view.content.CreatePanel;
import java.awt.Color;
import java.util.Stack;
import javax.swing.JPanel;
import static view.LayoutConstants.*;

/**
 *
 * @author tim
 */
public class ContentPane extends JPanel {
    
    private CreatePanel noteadd;
    private Stack thoughts = new Stack();
    
    public ContentPane(int x, int y, int w, int h) {
        super(null);
        
        setBounds(x, y, w, h);
        setBackground(Color.red);
        
        add(noteadd = new CreatePanel(0, 0, CREATEPANEL_WIDTH, CREATEPANEL_HEIGHT));
        
        
    }
    
}
