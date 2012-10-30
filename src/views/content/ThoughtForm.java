package views.content;

import controller.Controller;
import models.Thought;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ThoughtForm extends JPanel {

    private JTextArea textArea;

    public ThoughtForm(final Controller controller) {
        setLayout(new MigLayout("", "[][grow]", ""));
        generateForm(controller);

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

    public ThoughtForm(final Controller controller, final Thought thought) {
        setLayout(new MigLayout("ins 0", "[][grow]", ""));
        generateForm(controller);
        textArea.setText(thought.getNotes());
        JButton opslaan = new JButton("Opslaan");
        opslaan.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                thought.setNotes(textArea.getText());
                controller.saveThought(thought);
            }
        });
        add(opslaan, "span, align right");
    }

    private void generateForm(final Controller controller) {

        setBackground(Color.white);

        add(new JLabel("Notities:"), "wrap");
        textArea = new JTextArea(3, 20);
        add(new JScrollPane(textArea), "span, grow");


    }
}
