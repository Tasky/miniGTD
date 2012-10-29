package views.content;

import models.Task;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/**
 * Author: nanne
 */
public class TaskItem extends ItemPanel {
    public TaskItem(Task task) {
        setOpaque(false);
        setLayout(new MigLayout("", "[grow][]", "[]"));
        // TODO: knopje toevoegen bij aanpassen textarea
        add(new JTextArea(task.getNotes()), "growx, aligny top");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new MigLayout("ins 0"));
        buttonPanel.setOpaque(false);
        // TODO: knopjes werkend maken
        JButton btnOpslaan = new JButton("Opslaan");
        btnOpslaan.setEnabled(false);
        buttonPanel.add(btnOpslaan, "span, growx");
        buttonPanel.add(new JButton("Maak actie"), "span, growx");
        buttonPanel.add(new JButton("Weggooien"), "span, growx");

        add(buttonPanel);
    }
}
