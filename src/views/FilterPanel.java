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

public class FilterPanel extends JPanel {
    private Stack<Tab> tabs = new Stack<Tab>();

    public FilterPanel(final Controller controller) {
        super(null);
        
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new MigLayout("ins 0", "[grow]"));
        setBackground(new Color(230, 238, 251));

        add(new Header("Verzamelen"), "span, growx");
        tabs.push(new Tab("inbox_empty.png", "Inbox (2)", "inbox"));
        add(tabs.peek(), "span, growx");

        add(new Header("Focus"), "span, growx");
        tabs.push(new Tab("star.png", "Vandaag (2)", "today"));
        add(tabs.peek(), "span, growx");
        tabs.push(new Tab("date_next.png", "Volgende", "next"));
        add(tabs.peek(), "span, growx");
        tabs.push(new Tab("date_task.png", "Gepland", "planned"));
        add(tabs.peek(), "span, growx");
        tabs.push(new Tab("box_open.png", "Ooit", "ever"));
        add(tabs.peek(), "span, growx");

        add(new Header("Projecten"), "span, growx");
        class ProjectPopup extends JPopupMenu {
            public ProjectPopup(){
                add(new JMenuItem("Hernoemen"));
                add(new JMenuItem("Verwijderen"));
            }
        }
        for (int i = 0; i < 10; i++) {
            Tab tab = new Tab("to_do_list.png", "Java5", "project");
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
        tabs.push(new Tab("book.png", "Logboek", "history"));
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

    private class Header extends JLabel {
        public Header(String text) {
            super(text);
            setFont(new Font("Sans-serif", Font.BOLD, 13));

            Border padding = BorderFactory.createEmptyBorder(10, 10, 2, 10);
            setBorder(padding);
        }
    }

    private class Tab extends JLabel {
        private String action;

        public Tab(String image, String text, String action) {
            super();
            this.action = action;
            setText(text);
            setIcon(new ImageIcon(getClass().getResource("/resources/icons/" + image)));
            setFont(new Font("Sans-serif", Font.PLAIN, 11));
            setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 0));
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

        public String getAction() {
            return action;
        }
    }
}