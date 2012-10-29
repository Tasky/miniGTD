/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.filter;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author tim
 */
public class ProjectPopup extends JPopupMenu {

    public ProjectPopup() {
        add(new JMenuItem("Hernoemen"));
        add(new JMenuItem("Verwijderen"));
    }
}