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
    
    public AppFrame() {
        super(APP_NAME);
        
        setVisible(true);
        
        setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
    }
    
}
