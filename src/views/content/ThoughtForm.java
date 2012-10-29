package views.content;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ThoughtForm extends JPanel {
    public ThoughtForm() {
        super(null);
        setLayout(new MigLayout("", "[][grow]", ""));
        setBackground(Color.white);
        add(new JLabel("Notities:"), "wrap");
        add(new JScrollPane(new JTextArea(3, 20)), "span, grow");

        add(new JButton("Toevoegen"), "span, align right");
    }
}
