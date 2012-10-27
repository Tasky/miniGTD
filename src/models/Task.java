package models;

import util.DataLayer;
import util.exceptions.ConnectionException;

import java.util.Date;
import java.sql.*;

/**
 * Author: nanne
 */
public class Task {
    private int id = -1;
    private String description;
    private String status;
    private String notes;
    private String context;
    private String project;
    private Date action_date;
    private Date statuschange_date;
    private boolean done;
    private boolean isNew = true;

    // new task
    public Task() {
        isNew = true;
    }
//    // outside initialisation
//    public Task(String description, String notes, Date action_date, Date statuschange_date, boolean done) {
//        isNew = false;
//        this.description = description;
//        this.notes = notes;
////        this.context = context;
////        this.project = project;
//        this.action_date = action_date;
//        this.statuschange_date = statuschange_date;
//        this.done = done;
//    }
    // get data from db
    public Task(int id) throws ConnectionException {
        isNew = false;
        PreparedStatement statement = null;
        try {
            this.id = id;
            statement = DataLayer.getConnection().prepareStatement("select " +
                    "contexts.name as context, " +
                    "projects.name as project, " +
                    "status.name as status, " +
                    "action_date, " +
                    "description, " +
                    "done, " +
                    "notes, " +
                    "statuschange_date " +
                    "from actions " +
                    "left join contexts on context_id = contexts.id " +
                    "left join projects on project_id = projects.id " +
                    "left join statuses on status_id = status.id " +
                    "where actions.id = ?");

            statement.setInt(1, id);
            if (!statement.execute()) throw new ConnectionException();

            ResultSet result = statement.getResultSet();
            this.context = result.getString(1);
            this.project = result.getString(2);
            this.status = result.getString(3);
            this.action_date = result.getDate(4);
            this.description = result.getString(5);
            this.done = result.getBoolean(6);
            this.statuschange_date = result.getDate(7);
        } catch (SQLException e) {
            throw new ConnectionException();
        } finally {
            try {
                assert statement != null;
                statement.close();
            } catch (SQLException e) { }
        }
    }

    public void remove() throws ConnectionException {
        if (!isNew) {
            PreparedStatement statement = null;
            try {
                statement = DataLayer.getConnection().prepareStatement(
                        "delete from actions where id = ?;"
                );
                statement.setInt(1, id);
                if(statement.executeUpdate() == 1) {
                    id = -1;
                    isNew = true;
                }
            } catch (SQLException e) {
                throw new ConnectionException();
            }
        }
    }
    public void save() throws ConnectionException {
            PreparedStatement statement = null;
            try {
                if (isNew) {
                    statement = DataLayer.getConnection().prepareStatement(
                            "insert into actions values (null, ?, ?, ?, ?, ?, ?, ?, ?);"
                    );
                } else {
                    statement = DataLayer.getConnection().prepareStatement(
                            "update actions set " +
                                    "context_id = ?, " +
                                    "project_id = ?, " +
                                    "status_id = ?, " +
                                    "action_date = ?, " +
                                    "description = ?, " +
                                    "done = ?, " +
                                    "notes = ?, " +
                                    "statuschange_date = ? " +
                                    "where id = ?;"
                    );
                    statement.setInt(9, id);
                }

                // context_id
                statement.setNull(1, Types.INTEGER);
                // statement.setInt(1, 1);
                // project_id
                statement.setNull(2, Types.INTEGER);
                //statement.setInt(2, 5);
                // status_id
                statement.setNull(3, Types.INTEGER);
                //statement.setInt(2, 5);
                // action_date
                if (action_date == null)
                    statement.setNull(4, Types.DATE);
                else statement.setDate(4, new java.sql.Date(action_date.getTime()));
                // description
                statement.setString(5, description);
                // done
                statement.setBoolean(6, done);
                // notes
                statement.setString(7, notes);
                // statuschange_date
                if (statuschange_date == null)
                    statement.setNull(8, Types.DATE);
                else statement.setDate(8, new java.sql.Date(statuschange_date.getTime()));

                statement.executeUpdate();
                if (isNew) {
                    ResultSet rs = statement.getGeneratedKeys();
                    id = rs.getInt(1);
                    isNew = false;
                }
            } catch (SQLException e) {
                throw new ConnectionException();
            } finally {
                assert statement != null;
                try {
                    statement.close();
                } catch (SQLException e) { }
            }
    }

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

    public Date getActionDate() {
        return action_date;
    }

    public void setActionDate(Date action_date) {
        this.action_date = action_date;
    }

    public Date getStatuschangeDate() {
        return statuschange_date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        this.statuschange_date = new java.util.Date();
    }
}
