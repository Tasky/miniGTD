/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import models.Task;
import models.Thought;
import util.exceptions.ConnectionException;
import views.MainWindow;

/**
 *
 * @author tim
 */
public class Controller {
    
    private MainWindow frame;
    
    public Controller() {
        frame = new MainWindow(this);
        open("inbox");
    }

    public void open(String action) {
        try {
            frame.setTitle(getActionName(action));
            if (action.equals("inbox")) {
                frame.showThoughts(Thought.all());
            } else if (action.equals("today")) {
                frame.showTasks(Task.where(Task.Filter.TODAY));
            } else if (action.equals("next")) {
                frame.showTasks(Task.all());
            }
        } catch (ConnectionException e) {
            frame.showConnectionError();
            e.printStackTrace();
        }
    }

    public String getActionName(String action) {
        if (action.equals("inbox")) {
            return "Inbox (2)";
        } else if (action.equals("today")) {
            return "Vandaag";
        } else if (action.equals("next")) {
            return "Volgende";
        } else if (action.equals("planned")) {
            return "Gepland";
        } else if (action.equals("ever")) {
            return "Ooit";
        } else if (action.equals("history")) {
            return "Logboek";
        } else {
            return "";
        }
    }
}
