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
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Task.Sort;

/**
 *
 * @author tim
 */
public class Controller implements Observer {
    
    private MainWindow frame;
    private Sort order = Sort.ORDER;
    private boolean asc = true;
    private Task.Filter filter;
    private boolean formEnabled = true;
    private String action = "inbox";

    public Controller() {
        frame = new MainWindow(this);
        open(action);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                DataLayer.closeConnection();
            }
        });
    }
    
    public void sort(Sort order, boolean asc) {
        this.order = order;
        this.asc = asc;
        refreshTasks();
    }

    private void refreshTasks() {
        try {
            frame.updateTasks(Task.all(filter, order, asc), order, asc, formEnabled);
        } catch (ConnectionException e) {
            frame.showConnectionError();
            e.printStackTrace();
        }
    }

    public void open(String action) {
        this.action = action;
        try {
            frame.setTitle(getActionName(action));
            if (action.equals("inbox")) {
                this.showThoughts(Thought.all(), true);
                return;
            }

            formEnabled = true;
            if (action.equals("today")) {
                filter = Task.Filter.TODAY;
            } else if (action.equals("next")) {
                filter = Task.Filter.NEXT;
            } else if (action.equals("planned")) {
                filter = Task.Filter.PLANNED;
            } else if (action.equals("ever")) {
                filter = Task.Filter.EVER;
            } else if (action.equals("history")) {
                formEnabled = false;
                filter = Task.Filter.HISTORY;
            } else if (action.startsWith("project_")) {
                formEnabled = false;
                Integer project_id = Integer.valueOf(action.substring("project_".length()));
                filter = Task.Filter.PROJECT;
                Task.Filter.PROJECT.setProject_id(project_id);
            }

            frame.showTasks(Task.all(filter, order, asc), order, asc, formEnabled);
        } catch (ConnectionException e) {
            frame.showConnectionError();
            e.printStackTrace();
        }
    }

    private void showThoughts(List<Thought> all, boolean b) {
        for(Thought t : all)
            t.addObserver(this);

        frame.showThoughts(all);
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
            for(Project p : tmp) {
                p.addObserver(this);
                list.add(p);
            }

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
            } else if (action.startsWith("project_")) {
                Integer project_id = Integer.valueOf(action.substring("project_".length()));
                Project p = new Project(project_id);
                return "Project " + p.getName();
            }
        } catch (ConnectionException ignored) { }
        return "";
    }

    public void add(Task task) {
        task.addObserver(this);
        try {
            task.save();
        } catch (ConnectionException e) {
            e.printStackTrace();
            frame.showConnectionError();
        }
    }

    public void add(Thought thought) {
        thought.addObserver(this);
        try {
            thought.save();
        } catch (ConnectionException e) {
            e.printStackTrace();
            frame.showConnectionError();
        }
    }


    public void add(Project p) {
        p.addObserver(this);
        try {
            p.save();
        } catch (ConnectionException e) {
            e.printStackTrace();
            frame.showConnectionError();
        }
    }

    public void save(Task task) {
        try {
            task.save();
        } catch (ConnectionException e) {
            e.printStackTrace();
            frame.showConnectionError();
        }
    }

    public void save(Thought thought) {
        try {
            thought.save();
        } catch (ConnectionException e) {
            e.printStackTrace();
            frame.showConnectionError();
        }
    }

    public void save(Project p) {
        try {
            p.save();
        } catch (ConnectionException e) {
            e.printStackTrace();
            frame.showConnectionError();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        frame.setTitle(getActionName(action));
        try{
            if( arg instanceof Thought) {
                frame.updateThoughts(Thought.all());
            } else if (arg instanceof Task) {
                refreshTasks();
            }
            frame.updateFilters();
        }catch(ConnectionException e){
            e.printStackTrace();
            frame.showConnectionError();
        }
    }

    public String getAction() {
        return action;
    }

    public void remove(Thought t) {
        try {
            t.remove();
        } catch (ConnectionException ex) {
            ex.printStackTrace();
        }
    }
}