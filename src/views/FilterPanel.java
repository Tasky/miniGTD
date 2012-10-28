/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.Controller;

import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
import javax.swing.*;
import javax.swing.border.Border;

public class FilterPanel extends JPanel {
    private Stack<Tab> tabs = new Stack<Tab>();

    public FilterPanel(Controller controller) {
        super(null);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(230, 238, 251));

        add(new Header("Verzamelen"));
        tabs.push(new Tab("inbox_empty.png", "Inbox (2)"));
        add(tabs.peek());

        add(new Header("Focus"));
        tabs.push(new Tab("star.png", "Vandaag (2)"));
        add(tabs.peek());
        tabs.push(new Tab("date_next.png", "Volgende"));
        add(tabs.peek());
        tabs.push(new Tab("date_task.png", "Gepland"));
        add(tabs.peek());
        tabs.push(new Tab("box_open.png", "Ooit"));
        add(tabs.peek());

        add(new Header("Projecten"));
//        JScrollPane listScroller = new JScrollPane(list);
        tabs.push(new Tab("to_do_list.png", "Java5"));
        add(tabs.peek());
        tabs.push(new Tab("to_do_list.png", "Bedrijf starten"));
        add(tabs.peek());

        add(new Header("Archief"));
        tabs.push(new Tab("book.png", "Logboek"));
        add(tabs.peek());
        tabs.push(new Tab("bin_closed.png", "Prullenbak"));
        add(tabs.peek());
        tabs.get(0).setActive(true);
        final MouseListener click = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                Object source = mouseEvent.getSource();
                if (source instanceof Tab) {
                    for(Tab tab : tabs) {
                        tab.setActive(false);
                    }
                    Tab tab = (Tab) source;
                    tab.setActive(true);
                }
            }
        };
        for(Tab tab : tabs) {
            tab.addMouseListener(click);
        }
    }

    private class Header extends JLabel {
        public Header(String text) {
            super(text);
            setFont(new Font("Sans-serif", Font.BOLD, 13));

            Border padding = BorderFactory.createEmptyBorder(10, 10, 2, 10);
            setBorder(padding);
        }
    }

    private class Tab extends JLabel {
        public Tab(String image, String text) {
            super();
            setText(text);
            setIcon(new ImageIcon(getClass().getResource("/resources/icons/" + image)));
            setFont(new Font("Sans-serif", Font.PLAIN, 11));
            Border padding = BorderFactory.createEmptyBorder(2, 10, 2, 0);
            setBorder(padding);
            Dimension size = getPreferredSize();
            size.width = 150;
            setMaximumSize(size);
            setPreferredSize(size);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        public void setActive(boolean b) {
            if(b) {
                setBackground(new Color(197, 186, 244));
                setOpaque(true);
            } else {
                setBackground(null);
                setOpaque(false);
            }
        }
    }
}