/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.content;


import controller.Controller;
import models.Task;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;
import java.awt.*;
import java.text.DateFormat;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class TaskForm extends JPanel {

    private JXDatePicker dateChooser;
    private JComboBox context;
    private JTextArea notes;
    private JTextField description;
    private JComboBox status;
    private JButton button;

    public TaskForm(Controller controller) {
        super(null);
        setLayout(new MigLayout("", "[][grow]", ""));

        description = new JTextField();
        notes = new JTextArea(3, 20);

        context = new JComboBox(controller.getContexts().toArray());
        context.setEditable(true);

        dateChooser = new JXDatePicker();

        status = new JComboBox(controller.getStatuses().toArray());

        button = new JButton("Toevoegen");

        generateForm();
    }
    public TaskForm(Controller controller, Task task) {
        super(null);
        setLayout(new MigLayout("ins 0", "[][grow]", ""));

        description = new JTextField(task.getDescription());
        notes = new JTextArea(3, 20);
        notes.setText(task.getNotes());

        context = new JComboBox(controller.getContexts().toArray());
        context.setEditable(true);
        status.setSelectedItem(task.getContext());

        dateChooser = new JXDatePicker(task.getActionDate());
        status = new JComboBox(controller.getStatuses().toArray());
        status.setSelectedItem(task.getStatus());

        button = new JButton("Aanpassen");
        generateForm();
    }

    private void generateForm() {
        dateChooser.setLocale(getLocale());
        dateChooser.setFormats(DateFormat.getDateInstance());

        setBackground(Color.white);
        add(new JLabel("Actie:"));
        add(description, "span, grow");
        add(new JLabel("Notities:"), "wrap");
        add(new JScrollPane(notes), "span, grow");
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new MigLayout("ins 0", "[][grow]", ""));
        panel.add(generateField(new JLabel("Context:"), context));
        panel.add(new JLabel(""), "growx");
        panel.add(generateField(new JLabel("Datum:"), dateChooser));
        panel.add(new JLabel(""), "growx");
        panel.add(generateField(new JLabel("Status:"), status));
        panel.add(new JLabel(""), "growx");

        panel.add(button, "align right");
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
