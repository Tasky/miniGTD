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
public class NavPane extends JPanel {
    
    public NavPane(int x, int y, int w, int h) {
        super(null);

        setLocation(0, 0);
        setBounds(x, y, w, h);
        setBackground(Color.BLACK);
        
    }
    
}
