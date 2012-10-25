/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import views.content.NoteAdd;
import java.awt.Color;
import javax.swing.JPanel;
import static views.LayoutConstants.*;

/**
 *
 * @author tim
 */
public class ContentPane extends JPanel {
    
    private NoteAdd noteadd;
    
    public ContentPane(int x, int y, int w, int h) {
        super(null);
        
        setBounds(x, y, w, h);
        setBackground(Color.red);

        
        add(noteadd = new NoteAdd(0, 0, NOTEADD_WIDTH, NOTEADD_HEIGHT));
    }
    
}
