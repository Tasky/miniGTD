/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.Controller;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
import javax.swing.*;
import javax.swing.border.Border;
import views.filter.*;

public class FilterPanel extends JPanel {
    private Stack<Tab> tabs = new Stack<Tab>();
    private final Controller controller;

    public FilterPanel(final Controller controller) {
        super(null);
        this.controller = controller;

//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new MigLayout("ins 0", "[grow]"));
        setBackground(new Color(230, 238, 251));

        add(new Header("Verzamelen"), "span, growx");
        tabs.push(new Tab("inbox_empty.png", "inbox", controller));
        add(tabs.peek(), "span, growx");

        add(new Header("Focus"), "span, growx");
        tabs.push(new Tab("star.png", "today", controller));
        add(tabs.peek(), "span, growx");
        tabs.push(new Tab("date_next.png", "next", controller));
        add(tabs.peek(), "span, growx");
        tabs.push(new Tab("date_task.png", "planned", controller));
        add(tabs.peek(), "span, growx");
        tabs.push(new Tab("box_open.png", "ever", controller));
        add(tabs.peek(), "span, growx");

        add(new Header("Projecten"), "span, growx");

        for (int i = 0; i < 10; i++) {
            Tab tab = new Tab("to_do_list.png", "project", controller);
            tab.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent mouseEvent) {
                    if (mouseEvent.isPopupTrigger()) doPop(mouseEvent);
                }
                @Override
                public void mouseReleased(MouseEvent e){
                    if (e.isPopupTrigger()) doPop(e);
                }

                private void doPop(MouseEvent e){
                    ProjectPopup menu = new ProjectPopup();
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            });

            tabs.push(tab);
            add(tab, "span, growx");
        }

        add(new Header("Archief"), "span, growx");
        tabs.push(new Tab("book.png", "history", controller));
        add(tabs.peek(), "span, growx");
        //tabs.push(new Tab("bin_closed.png", "Prullenbak"));
        //add(tabs.peek(), "span, growx");
        tabs.get(0).setActive(true);
        final MouseListener click = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if(mouseEvent.isPopupTrigger()) return;
                Object source = mouseEvent.getSource();
                if (source instanceof Tab) {
                    for(Tab tab : tabs) {
                        tab.setActive(false);
                    }
                    Tab tab = (Tab) source;
                    tab.setActive(true);
                    controller.open(tab.getAction());
                }
            }
        };
        for(Tab tab : tabs) {
            tab.addMouseListener(click);
        }
    }

}