/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.filter;

import controller.Controller;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author tim
 */
    public class Tab extends JLabel {
        private String action;

        public Tab(String image, String action, Controller controller) {
            super();
            this.action = action;
            setText(controller.getActionName(action));
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