/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

/**
 *
 * @author tim
 */
public class LayoutConstants {
    
    public static final String APP_NAME = "GTD app";
    
    public static final int MARGIN = 10;

    public static final int MAINWINDOW_WIDTH = 800;
    public static final int MAINWINDOW_HEIGHT = 600;
    
    public static final int FILTERPANEL_WIDTH = 150;
    public static final int FILTERPANEL_HEIGHT = MAINWINDOW_HEIGHT;

    public static final int CONTENTPANE_WIDTH = MAINWINDOW_WIDTH-FILTERPANEL_WIDTH;
    public static final int CONTENTPANE_HEIGHT = MAINWINDOW_HEIGHT;
    
    public static final int CREATEPANEL_WIDTH = CONTENTPANE_WIDTH;
    public static final int CREATEPANEL_HEIGHT = 120;
    
    public static final int CREATETEXT_HEIGHT = (CREATEPANEL_HEIGHT/3);
    
    public static final int BUTTON_WIDTH = 120;
    public static final int BUTTON_HEIGHT = 25;
    
    public static final int TASK_WIDTH = CREATEPANEL_WIDTH-(MARGIN*2);
    public static final int TASK_HEIGHT = CREATEPANEL_HEIGHT;
    
}
