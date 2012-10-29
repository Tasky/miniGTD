package models;

import util.DataLayer;
import java.sql.*;

/**
 * Author: nanne
 */
public class Thought {
    private String notes;
    private boolean isNew;
    private int id = -1;

    public Thought(String notes) {
        this.notes = notes;
        this.isNew = true;
    }
    
    public Thought() {
        
    }

    public void toTask() {}
    public void save() throws SQLException {
        PreparedStatement statement = null;
        
        if(isNew) {
            statement = DataLayer.getConnection().prepareStatement("INSERT INTO thoughts SET notes = ?");
            statement.setString(1, notes);
            statement.executeQuery();
            ResultSet res = statement.getGeneratedKeys();
            this.id = res.getInt(1);
        }else{
            //get id and update
        }
        
        try{
            statement.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public void remove() {}
    public static void create(String notes) throws SQLException {
        PreparedStatement statement = null;
        
        statement = DataLayer.getConnection().prepareStatement("INSERT INTO thoughts SET notes = ?");
        statement.setString(1, notes);
        statement.execute();
        statement.close();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
