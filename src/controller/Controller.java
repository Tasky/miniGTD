/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import models.Status;
import models.Task;
import models.Thought;
import util.exceptions.ConnectionException;
import views.MainWindow;

import java.util.ArrayList;
import java.util.List;
import models.Project;

/**
 *
 * @author tim
 */
public class Controller {
    
    private MainWindow frame;
    
    public Controller() {
        frame = new MainWindow(this);
//        open("inbox");
        open("today");
    }

    public void open(String action) {
        try {
            frame.setTitle(getActionName(action));
            if (action.equals("inbox")) {
                frame.showThoughts(Thought.all());
            } else if (action.equals("today")) {
                frame.showTasks(Task.where(Task.Filter.TODAY));
            } else if (action.equals("next")) {
                frame.showTasks(Task.where(Task.Filter.NEXT));
            } else if (action.equals("planned")) {
                frame.showTasks(Task.where(Task.Filter.PLANNED));
            } else if (action.equals("ever")) {
                frame.showTasks(Task.where(Task.Filter.EVER));
            }
        } catch (ConnectionException e) {
            frame.showConnectionError();
            e.printStackTrace();
        }
    }

    public List<String> getStatuses() {
        List<String> list = new ArrayList<String>();
        list.add("");
        try {
            List<Status> statuses = Status.all();
            for (Status status : statuses) {
                list.add(status.getName());
            }
        } catch (ConnectionException e) {
            frame.showConnectionError();
        }
        return list;
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
    
    public List<Project> getProjects() {
        List<Project> list = new ArrayList<Project>();
        try{
           List<Project> tmp = Project.all();
           for(Project p : tmp)
               list.add(p);
        
        }catch(ConnectionException e) {
            frame.showConnectionError();
        }
        return list;
    }
}
