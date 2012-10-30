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
import models.Task.Sort;

/**
 *
 * @author tim
 */
public class Controller implements Observer {
    
    private MainWindow frame;
    private Sort order = Sort.ORDER;
    
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
    
    public void sort(Sort s) {
        order = s;
    }

    public void open(String action) {
        try {
            frame.setTitle(getActionName(action));
            if (action.equals("inbox")) {
                this.showThoughts(Thought.all(), true);
            } else if (action.equals("today")) {
                frame.showTasks(Task.all(Task.Filter.TODAY), order, true, true);
            } else if (action.equals("next")) {
                frame.showTasks(Task.all(Task.Filter.NEXT), order, true, true);
            } else if (action.equals("planned")) {
                frame.showTasks(Task.all(Task.Filter.PLANNED), order, true, true);
            } else if (action.equals("ever")) {
                frame.showTasks(Task.all(Task.Filter.EVER), order, true, true);
            } else if (action.equals("history")) {
                frame.showTasks(Task.all(Task.Filter.HISTORY), false);
            }
        } catch (ConnectionException e) {
            frame.showConnectionError();
            e.printStackTrace();
        }
    }

    private void showThoughts(List<Thought> all, boolean b) {
        for(Thought t : all)
            t.addObserver(this);

        frame.showThoughts(all, b);
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
        thought.addObserver(this);
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
        System.out.println("bier");
        try{
            if( arg instanceof Thought) {
                frame.showThoughts(Thought.all(), false);
            }
        }catch(ConnectionException e){
            e.printStackTrace();
        }
    }
}