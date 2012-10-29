/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.filter;

import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author tim
 */
public class Header extends JLabel {

    public Header(String text) {
        super(text);
        setFont(new Font("Sans-serif", Font.BOLD, 13));

        Border padding = BorderFactory.createEmptyBorder(10, 10, 2, 10);
        setBorder(padding);
    }
}
