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
public class MainWindow extends JFrame {
    
    private FilterPanel navpane;
    private ContentPane contentpane;
    
    public MainWindow() {
        super(APP_NAME);
        setLayout(null);
        setBounds(0, 0, MAINWINDOW_WIDTH, MAINWINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        
        add(navpane = new FilterPanel(0, 0, FILTERPANEL_WIDTH, FILTERPANEL_HEIGHT));
        add(contentpane = new ContentPane(FILTERPANEL_WIDTH, 0, CONTENTPANE_WIDTH, CONTENTPANE_HEIGHT));
        
        setVisible(true);
    }
    
}
