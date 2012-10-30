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
    public class Tab extends IconLabel {
        private String action;
        private static Tab active;

        public Tab(String image, String action, final Controller controller) {
            super(image);
            this.action = action;
            if(controller.getAction().equals(action)) {
                setActive(true);
            }
            setText(controller.getActionName(action));
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