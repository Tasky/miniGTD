/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class FilterPanel extends JPanel {

    public FilterPanel() {
        super(null);

        //setBounds(x, y, w, h);
//        JScrollPane listScroller = new JScrollPane(list);
//        listScroller.setPreferredSize(new Dimension(250, 80));
//        listScroller.setAlignmentX(LEFT_ALIGNMENT);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        setLayout(new GridLayout(0,1));
        setBackground(new Color(230,238,251));

        add(new Header("Verzamelen"));
        add(new Tab("inbox_empty.png", "Inbox (2)"));

        add(new Header("Focus"));
        Tab tab = new Tab("star.png", "Vandaag (2)");
        add(tab);
        tab.setActive();
        add(new Tab("date_next.png", "Volgende"));
        add(new Tab("date_task.png", "Gepland"));
        add(new Tab("box_open.png", "Ooit"));

        add(new Header("Projecten"));
        add(new Tab("to_do_list.png", "Java5"));
        add(new Tab("to_do_list.png", "Bedrijf starten"));

        add(new Header("Archief"));
        add(new Tab("book.png", "Logboek"));
        add(new Tab("bin_closed.png", "Prullenbak"));
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
        }
        public void setActive() {
            setOpaque(true);
            setBackground(new Color(197, 186, 244));
//            setFont(new Font("Sans-serif", Font.BOLD, 11));
        }
    }
}