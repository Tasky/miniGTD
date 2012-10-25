/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author tim
 */
public class NavPane extends JPanel {
    
    public NavPane(int x, int y, int w, int h) {
        super(null);

        setBounds(x, y, w, h);
        setBackground(Color.BLACK);
        
    }
    
}
