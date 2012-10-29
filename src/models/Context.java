package models;

import util.DataLayer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.exceptions.ConnectionException;

/**
 * Author: tim
 */
public class Context implements Item {
    private String name = "";
    private boolean isNew;
    private int id = -1;

    public Context(String name) {
        this.name = name;
        this.isNew = true;
    }
    
    public Context(int id) {
        //TODO: get info from SQL
    }
    
    public Context() {
        
    }

    public String getNote() {
        return this.name;
    }
    
    public void save() throws ConnectionException {
        PreparedStatement statement = null;
        
        if(isNew) {
            try {
                statement = DataLayer.getConnection().prepareStatement("INSERT INTO contexts SET notes = ?");
                statement.setString(1, name);
                statement.executeQuery();
                ResultSet res = statement.getGeneratedKeys();
                this.id = res.getInt(1);
            } catch (SQLException ex) {
                throw new ConnectionException();
            }
        }else if(id > 0){
            try {
                statement = DataLayer.getConnection().prepareStatement("UPDATE contexts SET name = ? WHERE id = ?");
                statement.setString(1, name);
                statement.setInt(2, id);
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
                statement = DataLayer.getConnection().prepareStatement("DELETE FROM contexts WHERE id = ? LIMIT 1");
                statement.setInt(1, id);
                statement.execute();
                statement.close();
            } catch (SQLException ex) {
               throw new ConnectionException();
            }
        }else
            throw new ConnectionException();
        
    }
    
    public static void create(String notes) throws ConnectionException {
        try {
            PreparedStatement statement = null;
            
            statement = DataLayer.getConnection().prepareStatement("INSERT INTO contexts SET notes = ?");
            statement.setString(1, notes);
            statement.execute();
            statement.close();
        } catch (SQLException ex) {
            throw new ConnectionException();
        }
    }
    
    public static List<Context> all() throws ConnectionException {
        List<Context> list = new ArrayList<Context>();
        PreparedStatement statement = null;
            
        try{

            statement = DataLayer.getConnection().prepareStatement("SELECT id, name FROM contexts");
            statement.execute();
            ResultSet res = statement.getResultSet();

            while(res.next()) {
                Context t = new Context();
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
    }
    
    

    public String getNotes() {
        return name;
    }

    public void setNotes(String name) {
        this.name = name;
    }
}