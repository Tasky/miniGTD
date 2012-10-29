/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.content;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Thought extends JPanel {
    
    public Thought(models.Thought thought) {
        setOpaque(false);
        setLayout(new MigLayout("", "[grow][]", "[]"));
        // TODO: knopje toevoegen bij aanpassen textarea
        add(new JTextArea(thought.getNotes()), "growx, aligny top");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new MigLayout("ins 0"));
        buttonPanel.setOpaque(false);
        // TODO: knopjes werkend maken
        buttonPanel.add(new JButton("Maak actie"), "span");
        buttonPanel.add(new JButton("Weg ermee"));
        add(buttonPanel);
    }

    @Override
    public void paint(Graphics g2) {
        Graphics2D g = (Graphics2D) g2;
        Dimension originalSize = super.getSize();

        // rounded corners
        g.setColor(Color.white);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRoundRect(0, 0, originalSize.width, originalSize.height, 10, 10);
        // draw the rest
        super.paint(g);
    }
}
