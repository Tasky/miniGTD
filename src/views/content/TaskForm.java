/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.content;


import controller.Controller;
import models.Status;
import models.Task;
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

    private JXDatePicker dateChooser;
    private JComboBox context;
    private JTextArea notes;
    private JTextField description;
    private JComboBox status;
    private JButton button;

    public TaskForm(final Controller controller) {
        super(null);
        setLayout(new MigLayout("", "[][grow]", ""));

        description = new JTextField();
        notes = new JTextArea(3, 20);

        context = new JComboBox(controller.getContexts().toArray());
        context.setEditable(true);

        dateChooser = new JXDatePicker();

        status = new JComboBox(controller.getStatuses().toArray());

        button = new JButton("Toevoegen");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Task task = new Task();
                task.setActionDate(dateChooser.getDate());
                task.setContext((String) context.getSelectedItem());
                task.setDescription(description.getText());
                task.setNotes(notes.getText());
                task.setStatus((Status) status.getSelectedItem());
                controller.saveTask(task);
            }
        });
        generateForm();
    }
    public TaskForm(final Controller controller, final Task task) {
        super(null);
        setLayout(new MigLayout("ins 0", "[][grow]", ""));

        description = new JTextField(task.getDescription());
        notes = new JTextArea(3, 20);
        notes.setText(task.getNotes());

        context = new JComboBox(controller.getContexts().toArray());
        context.setEditable(true);
        context.setSelectedItem(task.getContext());

        dateChooser = new JXDatePicker(task.getActionDate());
        status = new JComboBox(controller.getStatuses().toArray());
        status.setSelectedItem(task.getStatus());

        button = new JButton("Aanpassen");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                task.setActionDate(dateChooser.getDate());
                task.setContext((String) context.getSelectedItem());
                task.setDescription(description.getText());
                task.setNotes(notes.getText());
                task.setStatus((Status) status.getSelectedItem());
                controller.saveTask(task);
            }
        });
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
