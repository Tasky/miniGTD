/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package views.content;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ThoughtItem extends ItemPanel {

    public ThoughtItem(models.Thought thought) {
        setOpaque(false);
        setLayout(new MigLayout("", "[grow][]", "[]"));
        // TODO: knopje toevoegen bij aanpassen textarea
        add(new JTextArea(thought.getNotes()), "growx, aligny top");
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
