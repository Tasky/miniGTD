/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.JFrame;
import static views.LayoutConstants.*;

/**
 *
 * @author tim
 */
public class MainWindow extends JFrame {
    
    private NavPane navpane;
    private ContentPane contentpane;
    
    public MainWindow() {
        super(APP_NAME);
        setLayout(null);
        setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        
        add(navpane = new NavPane(0, 0, NAVPANE_WIDTH, NAVPANE_HEIGHT));
        add(contentpane = new ContentPane(NAVPANE_WIDTH, 0, CONTENTPANE_WIDTH, CONTENTPANE_HEIGHT));
        
        setVisible(true);
    }
    
}
