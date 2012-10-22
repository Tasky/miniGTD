/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JFrame;
import static view.LayoutConstants.*;

/**
 *
 * @author tim
 */
public class AppFrame extends JFrame {
    
    private NavPane navpane;
    
    public AppFrame() {
        super(APP_NAME);
        setLayout(null);
        setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        
        add(navpane = new NavPane(0, 0, NAVPANE_WIDTH, NAVPANE_HEIGHT));
        
        setVisible(true);
    }
    
}
