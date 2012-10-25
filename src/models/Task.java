package models;

import java.sql.Date;

/**
 * Author: nanne
 */
public class Task {
    private String description;
    private String notes;
    private String context;
    private String project;
    private Date action_date;
    private Date statuschange_date;
    private boolean done;

    public Task(String description, String notes, String context, String project, Date action_date, Date statuschange_date, boolean done) {
        this.description = description;
        this.notes = notes;
        this.context = context;
        this.project = project;
        this.action_date = action_date;
        this.statuschange_date = statuschange_date;
        this.done = done;
    }

    public static void create(){}
    public void remove(){}
    public void save(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Date getAction_date() {
        return action_date;
    }

    public void setAction_date(Date action_date) {
        this.action_date = action_date;
    }

    public Date getStatuschange_date() {
        return statuschange_date;
    }

    public void setStatuschange_date(Date statuschange_date) {
        this.statuschange_date = statuschange_date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
