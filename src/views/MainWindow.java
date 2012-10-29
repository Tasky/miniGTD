/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.Controller;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author tim
 */
public class MainWindow extends JFrame {

    private FilterPanel navpane;
    private ContentPanel contentpane;

    public MainWindow(Controller controller) {
        super("miniGTD");
        setLayout(null);
        setBounds(0, 0, 900, 700);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900, 700));

        setLayout(new MigLayout("ins 0, fill, gap 0", "[][grow]", "[grow]"));

        navpane = new FilterPanel(controller);

        JScrollPane scroller = new JScrollPane(navpane);
        scroller.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
        scroller.setMinimumSize(new Dimension(200, 400));
        add(scroller, "growy");
        navpane.setMinimumSize(new Dimension(200, 400));

        contentpane = new ContentPanel(controller);
        JScrollPane contentScroller = new JScrollPane(contentpane);
        contentScroller.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
        add(contentScroller, "span, grow");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
 
}
