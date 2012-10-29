/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.content;

import com.toedter.calendar.JDateChooser;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import javax.swing.*;

public class CreatePanel extends JPanel {
    
    private JTextField textf;
    private JLabel label;
    private JButton addButton;
    private JButton nogwat;

    public CreatePanel() {
        super(null);
//GroupLayout: http://docs.oracle.com/javase/tutorial/uiswing/layout/group.html
//        setLayout();
        setLayout(new MigLayout("", "[][grow]", ""));
        setBackground(Color.white);

        add(new JLabel("Actie:"));
        add(new JTextField(), "span, grow");
        add(new JLabel("Notities:"), "wrap");

        add(new JScrollPane(new JTextArea(5, 20)), "span, grow");

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new MigLayout("ins 0", "[][grow]", ""));
        JComboBox context = new JComboBox(new String[]{"Bird", "Cat", "Dog", "Rabbit", "Pig"});
        context.setEditable(true);

        panel.add(generateField(new JLabel("Context:"), context));

        panel.add(new JLabel(""), "growx");


        panel.add(generateField(new JLabel("Datum:"), new JDateChooser()));

        panel.add(new JLabel(""), "growx");

        JComboBox status = new JComboBox(new String[]{"do ASAP", "do NIET ASAP"});
        status.setEditable(true);
        panel.add(generateField(new JLabel("Status:"), status));

        panel.add(new JLabel(""), "growx");

        panel.add(new JButton("Toevoegen"));
        add(panel, "span, growx");
    }

    private JComponent generateField(JLabel label, JComponent component) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(label);
        panel.add(component);
        return panel;
    }
}
