/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.filter;

import controller.Controller;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author tim
 */
    public class Tab extends JLabel {
        private String action;
        private static Tab active;

        public Tab(String image, String action, final Controller controller) {
            super();
            this.action = action;
            if(controller.getAction().equals(action)) {
                setActive(true);
            }
            setText(controller.getActionName(action));
            setIcon(new ImageIcon(getClass().getResource("/resources/icons/" + image)));
            setFont(new Font("Sans-serif", Font.PLAIN, 11));
            setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 0));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent mouseEvent) {
                    if(mouseEvent.isPopupTrigger()) return;
                    Object source = mouseEvent.getSource();
                    if (source instanceof Tab) {
                        Tab tab = (Tab) source;
                        tab.setActive(true);
                        controller.open(tab.getAction());
                    }
                }
            });
        }

        public void setActive(boolean b) {
            if(b) {
                if(active != null) {
                    active.setActive(false);
                }
                setBackground(new Color(197, 186, 244));
                setOpaque(true);
                active = this;
            } else {
                setBackground(null);
                setOpaque(false);
            }
        }

        public String getAction() {
            return action;
        }
    }