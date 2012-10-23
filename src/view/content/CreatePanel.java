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
public class CreatePanel extends JPanel {
    
    private JTextField textf;
    private JLabel label;
    private JButton addButton;
   
    public CreatePanel(int x, int y, int w, int h) {
        super(null);
        
        setBounds(x, y, w, h);
        setBackground(Color.blue);
        
        initComponents();
    }
    
    private void initComponents() {
        add(textf = new JTextField());
        add(label = new JLabel("Notities:"));
        add(addButton = new JButton("Toevoegen"));
        textf.setBounds(MARGIN, MARGIN*3, CREATEPANEL_WIDTH-(MARGIN*2), CREATETEXT_HEIGHT);
        label.setBounds(MARGIN, MARGIN, CREATEPANEL_WIDTH, MARGIN);
        addButton.setBounds(CREATEPANEL_WIDTH-(MARGIN+BUTTON_WIDTH), CREATEPANEL_HEIGHT-(MARGIN+BUTTON_HEIGHT ), BUTTON_WIDTH, 25);
        addButton.setVisible(true);
    }
    
}
