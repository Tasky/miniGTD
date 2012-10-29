/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import models.Thought;
import util.exceptions.ConnectionException;
import views.MainWindow;
import java.util.List;

/**
 *
 * @author tim
 */
public class Controller {
    
    private MainWindow frame;
    
    public Controller() {
        frame = new MainWindow(this);
    }

    public List<Thought> getAllThoughts() throws ConnectionException {
        try {
            return Thought.getAllThoughts();
        } catch (ConnectionException e) {
            frame.showConnectionError();
            throw e;
        }
    }
}
