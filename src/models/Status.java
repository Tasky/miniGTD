/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DataLayer;
import util.exceptions.ConnectionException;

/**
 *
 * @author tim
 */
public class Status {
    
    private int id;
    private String name;

    
    public static List<Status> all() throws ConnectionException {
        List<Status> list = new ArrayList<Status>();
        PreparedStatement statement = null;
            
        try {
            statement = DataLayer.getConnection().prepareStatement("SELECT id, name FROM statuses");
            statement.execute();
            ResultSet res = statement.getResultSet();

            while(res.next()) {
                Status t = new Status();
                t.fromResultSet(res);
                list.add(t);
            }
            statement.close();
            return list;
        }catch(SQLException e) {
            throw new ConnectionException(e.getMessage());
        }
    }
    
    private void fromResultSet(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getId() {
        return this.id;
    }
}
