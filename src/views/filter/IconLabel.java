package views.filter;

import javax.swing.*;
import java.awt.*;

/**
 * Author: nanne
 */
public class IconLabel extends JLabel {
    public IconLabel(String image) {
        setIcon(new ImageIcon(getClass().getResource("/resources/icons/" + image)));
        setFont(new Font("Sans-serif", Font.PLAIN, 11));
        setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 0));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
