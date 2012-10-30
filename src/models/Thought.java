package models;

import util.DataLayer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.exceptions.ConnectionException;

/**
 * Author: tim
 */
public class Thought extends Observable implements Item {
    private String notes = "";
    private boolean isNew;
    private int id = -1;

    public Thought(String notes) {
        this.notes = notes;
        this.isNew = true;
    }
    
    public Thought(int id) {
        //TODO: get info from SQL
    }
    
    public Thought() {
        this.isNew = true;
    }

    public String getNote() {
        return this.notes;
    }
    
    public void save() throws ConnectionException {
        PreparedStatement statement = null;
        
        if(isNew) {
            try {
                statement = DataLayer.getConnection().prepareStatement("INSERT INTO thoughts SET notes = ?", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, notes);
                statement.executeUpdate();
                ResultSet res = statement.getGeneratedKeys();
                if (res.next()) {
                    this.id = res.getInt(1);
                    isNew = false;
                }
                statement.close();
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getMessage());
            }
        }else if(id > 0){
            try {
                statement = DataLayer.getConnection().prepareStatement("UPDATE thoughts SET notes = ? WHERE id = ?");
                statement.setString(1, notes);
                statement.setInt(2, id);
                statement.execute();
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getMessage());
            }
        }
        
        setChanged();
        notifyObservers(this);
        
        try{
            if (statement != null) {
                statement.close();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public void remove() throws ConnectionException {
        PreparedStatement statement = null;
        
        if(id > 0) {
            try {
                statement = DataLayer.getConnection().prepareStatement("DELETE FROM thoughts WHERE id = ? LIMIT 1");
                statement.setInt(1, id);
                statement.execute();
                statement.close();
            } catch (SQLException ex) {
               throw new ConnectionException();
            }
        }else
            throw new ConnectionException();
 
        setChanged();
        notifyObservers(this);

    }
    
    public static void create(String notes) throws ConnectionException {
        
        Thought t = new Thought(notes);
        t.save();

    }
    
    public static List<Thought> all() throws ConnectionException {
        List<Thought> list = new ArrayList<Thought>();
        PreparedStatement statement = null;
            
        try{

            statement = DataLayer.getConnection().prepareStatement("SELECT id, notes FROM thoughts ORDER BY id DESC");
            statement.execute();
            ResultSet res = statement.getResultSet();

            while(res.next()) {
                Thought t = new Thought();
                t.fromResultSet(res);
                list.add(t);
            }
            statement.close();
            
            return list;
        }catch(SQLException e) {
            throw new ConnectionException();
        }
        
    }

    public static Integer count() throws ConnectionException {
        try{
            PreparedStatement statement = null;
            statement = DataLayer.getConnection().prepareStatement("SELECT count(*) FROM thoughts");
            statement.execute();
            ResultSet res = statement.getResultSet();

            if(res.next()) {
                return res.getInt(1);
            }
            statement.close();
            
            return 0;
        }catch(SQLException e) {
            throw new ConnectionException();
        }
    }

    private void fromResultSet(ResultSet rs) throws SQLException {
        this.isNew = false;
        this.id = rs.getInt("id");
        this.notes = rs.getString("notes");
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}