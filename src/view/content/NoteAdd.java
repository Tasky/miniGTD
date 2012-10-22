/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.content;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static view.LayoutConstants.*;

/**
 *
 * @author tim
 */
public class NoteAdd extends JPanel {
    
    private JTextField textf;
    private JLabel label;
    private JButton addButton;
   
    public NoteAdd(int x, int y, int w, int h) {
        super(null);
        
        setBounds(x, y, w, h);
        setBackground(Color.blue);
        
        initComponents();
    }
    
    private void initComponents() {
        add(textf = new JTextField());
        add(label = new JLabel("Notities:"));
        add(addButton = new JButton("Toevoegen"));
        textf.setBounds(MARGIN, MARGIN*3, NOTEADD_WIDTH-(MARGIN*2), NOTEADDTEXT_HEIGHT);
        label.setBounds(MARGIN, MARGIN, NOTEADD_WIDTH, MARGIN);
        addButton.setBounds(NOTEADD_WIDTH-(MARGIN+BUTTON_WIDTH), NOTEADD_HEIGHT-(MARGIN+BUTTON_HEIGHT), BUTTON_WIDTH, 25);
        addButton.setVisible(true);
    }
    
}
