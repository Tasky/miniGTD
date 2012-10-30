package models;

import util.DataLayer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import util.exceptions.ConnectionException;

public class Project extends Observable implements Item {
    private String notes = "";
    private String name = "";
    private boolean isNew;
    private int id = -1;

    public Project(int id) throws ConnectionException {
        isNew = false;
        PreparedStatement statement = null;
        try {
            statement = DataLayer.getConnection().prepareStatement("select id, name, notes " +
                    "from projects " +
                    "where id = ?");

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            fromResultSet(result);
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage());
        } finally {
            try {
                assert statement != null;
                statement.close();
            } catch (SQLException ignored) {
            }
        }
    }

    public Project() {
        this.isNew = true;
    }

    public void save() throws ConnectionException {
        PreparedStatement statement = null;
        if(isNew) {
            try {
                statement = DataLayer.getConnection().prepareStatement("INSERT INTO projects SET name = ?, notes = ?", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, name);
                statement.setString(2, notes);
                statement.executeUpdate();
                ResultSet res = statement.getGeneratedKeys();
                res.next();
                this.id = res.getInt(1);
                statement.close();
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getMessage());
            }
        } else {
            try {
                statement = DataLayer.getConnection().prepareStatement("UPDATE projects SET name = ?, notes = ? WHERE id = ?");
                statement.setString(1, name);
                statement.setString(2, notes);
                statement.setInt(3, id);
                statement.executeUpdate();
                statement.close();
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getMessage());
            }
        }
        this.setChanged();
        this.notifyObservers(this);
    }
    public void remove() throws ConnectionException {
        PreparedStatement statement = null;

        if(id > 0) {
            try {
                statement = DataLayer.getConnection().prepareStatement("DELETE FROM projects WHERE id = ? LIMIT 1");
                statement.setInt(1, id);
                statement.execute();
                statement.close();
                this.setChanged();
                this.notifyObservers(this);
            } catch (SQLException ex) {
               throw new ConnectionException();
            }
        }else
            throw new ConnectionException();

    }

    public static List<Project> all() throws ConnectionException {
        List<Project> list = new ArrayList<Project>();
        PreparedStatement statement = null;

        try {
            statement = DataLayer.getConnection().prepareStatement("SELECT id, name, notes FROM projects");
            statement.execute();
            ResultSet res = statement.getResultSet();

            while(res.next()) {
                Project t = new Project();
                t.fromResultSet(res);
                list.add(t);
            }
            statement.close();

            return list;
        }catch(SQLException e) {
            throw new ConnectionException();
        }

    }

    private void fromResultSet(ResultSet rs) throws SQLException {
        this.isNew = false;
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.notes = rs.getString("notes");
    }



    public String getName() {
        return name;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNotes(String note) {
        this.notes = note;
    }

    public int getId() {
        return id;
    }
}