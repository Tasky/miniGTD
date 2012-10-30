package views.content;

import controller.Controller;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ThoughtForm extends JPanel {


    public ThoughtForm(final Controller controller) {
        super(null);
        setLayout(new MigLayout("", "[][grow]", ""));
        setBackground(Color.white);

        generateForm(controller);
    }

    private void generateForm(final Controller controller) {
        add(new JLabel("Notities:"), "wrap");
        final JTextArea textArea = new JTextArea(3, 20);
        add(new JScrollPane(textArea), "span, grow");

        JButton toevoegen = new JButton("Toevoegen");
        toevoegen.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.addThought(textArea.getText());
                removeAll();
                generateForm(controller);
                revalidate();
                repaint();
            }
        });
        add(toevoegen, "span, align right");
    }
}
