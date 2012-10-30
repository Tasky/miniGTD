/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import models.Context;
import models.Status;
import models.Task;
import models.Thought;
import models.Project;
import util.DataLayer;
import util.exceptions.ConnectionException;
import views.MainWindow;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author tim
 */
public class Controller implements Observer {
    
    private MainWindow frame;
    
    public Controller() {
        frame = new MainWindow(this);
        open("inbox");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                DataLayer.closeConnection();
            }
        });
    }

    public void open(String action) {
        try {
            frame.setTitle(getActionName(action));
            if (action.equals("inbox")) {
                frame.showThoughts(Thought.all(), true);
            } else if (action.equals("today")) {
                frame.showTasks(Task.where(Task.Filter.TODAY), true);
            } else if (action.equals("next")) {
                frame.showTasks(Task.where(Task.Filter.NEXT), true);
            } else if (action.equals("planned")) {
                frame.showTasks(Task.where(Task.Filter.PLANNED), true);
            } else if (action.equals("ever")) {
                frame.showTasks(Task.where(Task.Filter.EVER), true);
            } else if (action.equals("history")) {
                frame.showTasks(Task.where(Task.Filter.HISTORY), false);
            }
        } catch (ConnectionException e) {
            frame.showConnectionError();
            e.printStackTrace();
        }
    }

    public List<Status> getStatuses() {
        try {
            return(Status.all());
        } catch (ConnectionException e) {
            frame.showConnectionError();
        }
        return new ArrayList<Status>();
    }

    public List<String> getContexts() {
        List<String> list = new ArrayList<String>();
        list.add("");
        try {
            List<Context> contexts = Context.all();
            for (Context context : contexts)
                list.add(context.getName());
        } catch (ConnectionException e) {
            frame.showConnectionError();
        }
        return list;
    }

    public List<Project> getProjects() {
        List<Project> list = new ArrayList<Project>();
        try {
            List<Project> tmp = Project.all();
            for(Project p : tmp)
                list.add(p);
        }catch(ConnectionException e) {
            frame.showConnectionError();
        }
        return list;
    }

    public String getActionName(String action) {
        try {
            if (action.equals("inbox")) {
                return "Inbox ("+Thought.count()+ ")";
            } else if (action.equals("today")) {
                return "Vandaag ("+Task.count(Task.Filter.TODAY)+ ")";
            } else if (action.equals("next")) {
                return "Volgende ("+Task.count(Task.Filter.NEXT)+ ")";
            } else if (action.equals("planned")) {
                return "Gepland ("+Task.count(Task.Filter.PLANNED)+ ")";
            } else if (action.equals("ever")) {
                return "Ooit ("+Task.count(Task.Filter.EVER)+ ")";
            } else if (action.equals("history")) {
                return "Logboek ("+Task.count(Task.Filter.HISTORY)+ ")";
            }
        } catch (ConnectionException ignored) { }
        return "";
    }

    public void addThought(String text) {
        Thought thought = new Thought();
        thought.setNotes(text);
        try {
            thought.save();
        } catch (ConnectionException e) {
            e.printStackTrace();
            frame.showConnectionError();
        }
    }

    public void saveTask(Task task) {
        try {
            task.save();
        } catch (ConnectionException e) {
            e.printStackTrace();
            frame.showConnectionError();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        try{
            if( arg instanceof Thought) {
                frame.showThoughts(Thought.all(), true);
            }
        }catch(ConnectionException e){
            e.printStackTrace();
        }
    }
}