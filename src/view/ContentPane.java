/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import javax.swing.JPanel;
import static view.LayoutConstants.*;

/**
 *
 * @author tim
 */
public class ContentPane extends JPanel {
    
    public ContentPane(int x, int y, int w, int h) {
        super(null);
        
        setBounds(x, y, w, h);
        setBackground(Color.red);
    }
    
}
