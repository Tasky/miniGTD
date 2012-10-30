/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.content;


import controller.Controller;
import models.Status;
import models.Task;
import models.Thought;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class TaskForm extends JPanel {

    private JXDatePicker dateChooser = new JXDatePicker();
    private JComboBox context;
    private JTextArea notes = new JTextArea(3, 20);
    private JTextField description = new JTextField();
    private JComboBox status;
    private JButton button;

    public TaskForm(final Controller controller) {
        super(null);
        setBackground(Color.white);
        setLayout(new MigLayout("", "[][grow]", ""));

        fillComboBoxes(controller);

        button = new JButton("Toevoegen");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Task task = new Task();
                applyToTask(task);
                controller.saveTask(task);
            }
        });
        generateForm();
    }
    public TaskForm(final Controller controller, final Task task) {
        super(null);
        setBackground(Color.white);
        setLayout(new MigLayout("ins 0", "[][grow]", ""));
        description.setText(task.getDescription());
        notes.setText(task.getNotes());

        fillComboBoxes(controller);

        status.setSelectedItem(task.getStatus());
        context.setSelectedItem(task.getContext());

        dateChooser.setDate(task.getActionDate());

        button = new JButton("Aanpassen");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                applyToTask(task);
                controller.saveTask(task);
            }
        });
        generateForm();
    }

    public void applyToTask(Task task) {
        task.setActionDate(dateChooser.getDate());
        task.setContext((String) context.getSelectedItem());
        task.setDescription(description.getText());
        task.setNotes(notes.getText());
        task.setStatus((Status) status.getSelectedItem());
    }

    public TaskForm(Controller controller, Thought thought) {
        super(null);
        setLayout(new MigLayout("ins 0", "[][grow]", ""));
        notes.setText(thought.getNotes());
        fillComboBoxes(controller);
        generateForm();
    }

    private void fillComboBoxes(Controller controller) {
        context = new JComboBox(controller.getContexts().toArray());
        context.setEditable(true);
        status = new JComboBox(controller.getStatuses().toArray());
    }

    private void generateForm() {
        dateChooser.setLocale(getLocale());
        dateChooser.setFormats(DateFormat.getDateInstance());

        add(new JLabel("Actie:"));
        add(description, "span, grow");
        add(new JLabel("Notities:"), "wrap");
        add(new JScrollPane(notes), "span, grow");
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new MigLayout("ins 0", "[][grow]", ""));
        context.setPreferredSize(new Dimension(120, context.getPreferredSize().height));
        context.setMaximumSize(new Dimension(120, context.getMaximumSize().height));
        panel.add(generateField(new JLabel("Context:"), context));
        panel.add(new JLabel(""), "growx");
        panel.add(generateField(new JLabel("Datum:"), dateChooser));
        panel.add(new JLabel(""), "growx");
        status.setPreferredSize(new Dimension(120, status.getPreferredSize().height));
        status.setMaximumSize(new Dimension(120, status.getMaximumSize().height));
        panel.add(generateField(new JLabel("Status:"), status));
        panel.add(new JLabel(""), "growx");

        if(button != null){
            panel.add(button, "align right");
        } else {
            panel.add(new JLabel(""), "align right");
        }

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
