package models;

import util.DataLayer;
import util.exceptions.ConnectionException;

import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.util.List;

/**
 * Author: nanne
 */
public class Task implements Item {
    private int id = -1;
    private String description = "";
    private String status = "";
    private String notes = "";
    private String context = "";
    private String project = "";
    private Date action_date;
    private Date statuschange_date;
    private boolean done = false;
    private boolean isNew = true;

    private static final String SQL = "actions.id as id, " +
            "contexts.name as context, " +
            "projects.name as project, " +
            "statuses.name as status, " +
            "actions.action_date as action_date, " +
            "actions.description as description, " +
            "actions.done as done, " +
            "actions.notes as notes, " +
            "actions.statuschange_date as statuschange_date ";

    /**
     * Set all properties from a resultset.
     *
     * @param result Result set from SQL constant.
     * @throws SQLException when a field does not exist
     */
    private void fromResultSet(ResultSet result) throws SQLException {
        isNew = false;
        this.id = result.getInt(1);
        this.context = result.getString(2);
        this.project = result.getString(3);
        this.status = result.getString(4);
        this.action_date = result.getDate(5);
        this.description = result.getString(6);
        this.done = result.getBoolean(7);
        this.notes = result.getString(8);
        this.statuschange_date = result.getDate(9);
    }

    public enum Filter {
        TODAY("not done and action_date <= NOW()"),
        NEXT("not done"),
        PLANNED("not done"),
        EVER("not done and statuses.name = 'ooit'"),
        HISTORY("done");

        private final String where;

        Filter(String where) {
            this.where = where;
        }

        public String getWhere() {
            return where;
        }

    }

    /**
     * New empty task
     */
    public Task() {
        isNew = true;
    }

    /**
     * Create a task and get data from DB.
     *
     * @param id task id
     * @throws ConnectionException when the connection fails
     */
    public Task(int id) throws ConnectionException {
        isNew = false;
        PreparedStatement statement = null;
        try {
            statement = DataLayer.getConnection().prepareStatement("select " + SQL +
                    " from actions " +
                    "left join contexts on context_id = contexts.id " +
                    "left join projects on project_id = projects.id " +
                    "left join statuses on status_id = statuses.id " +
                    "where actions.id = ?");

            statement.setInt(1, id);
            if (!statement.execute()) throw new ConnectionException();

            ResultSet result = statement.getResultSet();
            fromResultSet(result);
        } catch (SQLException e) {
            throw new ConnectionException();
        } finally {
            try {
                assert statement != null;
                statement.close();
            } catch (SQLException ignored) {
            }
        }
    }

    /**
     * Get a list of all tasks.
     *
     * @return list of tasks
     * @throws ConnectionException when the connection fails.
     */
    public static List<Task> all() throws ConnectionException {
        try {
            Statement stmt = DataLayer.getConnection().createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs = stmt.executeQuery("select " + SQL +
                    " from actions " +
                    "left join contexts on context_id = contexts.id " +
                    "left join projects on project_id = projects.id " +
                    "left join statuses on status_id = statuses.id");
            List<Task> tasks = new ArrayList<Task>();
            while (rs.next()) {
                Task t = new Task();
                t.fromResultSet(rs);
                tasks.add(t);
            }
            return tasks;
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage());
        }
    }

    /**
     * Get a count of tasks.
     * @param filter the filter
     * @return count
     * @throws ConnectionException database problems
     */
    public static Integer count(Filter filter) throws ConnectionException {
        try {
            Statement stmt = DataLayer.getConnection().createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs = stmt.executeQuery("select count(*) from actions " +
                    "left join contexts on context_id = contexts.id " +
                    "left join projects on project_id = projects.id " +
                    "left join statuses on status_id = statuses.id " +
                    "where " + filter.getWhere());
            if(rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage());
        }
    }

    /**
     * Get back all tasks filtered by given filter.
     *
     * @param filter The filter.
     * @return list of tasks
     * @throws ConnectionException when the connection fails.
     */
    public static List<Task> where(Filter filter) throws ConnectionException {
        try {
            Statement stmt = DataLayer.getConnection().createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs = stmt.executeQuery("select " + SQL +
                    " from actions " +
                    "left join contexts on context_id = contexts.id " +
                    "left join projects on project_id = projects.id " +
                    "left join statuses on status_id = statuses.id " +
                    "where " + filter.getWhere());
            List<Task> tasks = new ArrayList<Task>();
            while (rs.next()) {
                Task t = new Task();
                t.fromResultSet(rs);
                tasks.add(t);
            }
            return tasks;
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage());
        }
    }


    /**
     * Remove a task from the database, after removing you can add the task with save().
     *
     * @throws ConnectionException when the connection fails.
     */
    public void remove() throws ConnectionException {
        if (!isNew) {
            PreparedStatement statement = null;
            try {
                statement = DataLayer.getConnection().prepareStatement(
                        "delete from actions where id = ?;"
                );
                statement.setInt(1, id);
                if (statement.executeUpdate() == 1) {
                    id = -1;
                    isNew = true;
                }
            } catch (SQLException e) {
                throw new ConnectionException();
            }
        }
    }

    /**
     * Save the task to the database, automatically inserts the task or updates if it already exists.
     *
     * @throws ConnectionException when the connection fails.
     */
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
            } catch (SQLException ignored) {
            }
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
