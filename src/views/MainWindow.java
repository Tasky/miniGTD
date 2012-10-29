/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.Controller;
import models.Task;
import models.Thought;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

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
        contentScroller.setBorder(BorderFactory.createEmptyBorder());
        add(contentScroller, "span, grow");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void showConnectionError() {
        //TODO: omgaan met SQL problemen
        contentpane.setBackground(Color.RED);
    }

    public void showThoughts(List<Thought> thoughts) {
        contentpane.showThoughts(thoughts);
    }
    public void showTasks(List<Task> tasks) {
        contentpane.showTasks(tasks);
    }

    @Override
    public void setTitle(String title) {
        if (!title.isEmpty()) title += " - ";
        super.setTitle(title + "miniGTD");
    }
}
