/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

import static views.LayoutConstants.*;

/**
 *
 * @author tim
 */
public class MainWindow extends JFrame {

    private FilterPanel navpane;
    private ContentPanel contentpane;

    public MainWindow(Controller controller) {
        super(APP_NAME);
        setLayout(null);
        setBounds(0, 0, MAINWINDOW_WIDTH, MAINWINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(MAINWINDOW_WIDTH, MAINWINDOW_HEIGHT));

        setLayout(new GridBagLayout());

        // Default GridBagConstraints
        GridBagConstraints c = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,new Insets(0, 0, 0, 0), 0, 0);

        navpane = new FilterPanel(controller);
        // Layout configuration for filter panel
        c.gridx = 1;
        c.weightx = 0;
        c.fill = GridBagConstraints.VERTICAL;

        navpane.setMinimumSize(new Dimension(200, 400));
        JScrollPane scroller = new JScrollPane(navpane);
        scroller.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
        scroller.setMinimumSize(new Dimension(200, 400));
        add(scroller, c);

        contentpane = new ContentPanel(controller);
        // Layout configuration for content panel
        c.gridx = 2;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        add(contentpane, c);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
