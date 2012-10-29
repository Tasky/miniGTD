package models;

import util.DataLayer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.exceptions.ConnectionException;

/**
 * Author: tim
 */
public class Project implements Item {
    private String name = "";
    private String note = "";
    private boolean isNew;
    private int id = -1;

    public Project(String name) {
        this.name = name;
        this.isNew = true;
    }
    
    public Project(int id) {
        //TODO: get info from SQL
    }
    
    public Project() {
        
    }

    public String getNote() {
        return this.name;
    }
    
    public void save() throws ConnectionException {
        PreparedStatement statement = null;
        
        if(isNew) {
            try {
                statement = DataLayer.getConnection().prepareStatement("INSERT INTO projects SET name = ?, notes = ?");
                statement.setString(1, name);
                statement.setString(2, note);
                statement.executeQuery();
                ResultSet res = statement.getGeneratedKeys();
                this.id = res.getInt(1);
            } catch (SQLException ex) {
                throw new ConnectionException();
            }
        }else if(id > 0){
            try {
                statement = DataLayer.getConnection().prepareStatement("UPDATE projects SET name = ?, notes = ? WHERE id = ?");
                statement.setString(1, name);
                statement.setString(2, note);
                statement.setInt(3, id);
                statement.execute();
            } catch (SQLException ex) {
                throw new ConnectionException();
            }
        }
        
        try{
            statement.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public void remove() throws ConnectionException {
        PreparedStatement statement = null;
        
        if(id > 0) {
            try {
                statement = DataLayer.getConnection().prepareStatement("DELETE FROM projects WHERE id = ? LIMIT 1");
                statement.setInt(1, id);
                statement.execute();
                statement.close();
            } catch (SQLException ex) {
               throw new ConnectionException();
            }
        }else
            throw new ConnectionException();
        
    }
    
    public static void create(String name, String note) throws ConnectionException {
        try {
            PreparedStatement statement = null;
            
            statement = DataLayer.getConnection().prepareStatement("INSERT INTO projects SET name = ?, notes = ?");
            statement.setString(1, name);
            statement.setString(2, note);
            statement.execute();
            statement.close();
        } catch (SQLException ex) {
            throw new ConnectionException();
        }
    }
    
    public static List<Project> all() throws ConnectionException {
        List<Project> list = new ArrayList<Project>();
        PreparedStatement statement = null;
            
        try{

            statement = DataLayer.getConnection().prepareStatement("SELECT id, name, notes FROM projects");
            statement.execute();
            ResultSet res = statement.getResultSet();

            while(res.next()) {
                Project t = new Project();
                t.fromResultSet(res);
                list.add(t);
            }
            return list;
        }catch(SQLException e) {
            throw new ConnectionException();
        }
        
    }
    
    private void fromResultSet(ResultSet rs) throws SQLException {
        this.isNew = false;
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.note = rs.getString("notes");
    }
    
    

    public String getNotes() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
}