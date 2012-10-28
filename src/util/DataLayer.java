package util;

import models.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: nanne
 */
public class DataLayer {
    private static Connection con;
    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://databases.aii.avans.nl/tslot_db",
                        "tslot",
                        "b8I54LMZ");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }
    public static List<Task> getTasks() throws Exception {
        //TODO: betere exception
        if (con == null) throw new Exception("Er is nog geen connectie.");

        Statement statement = con.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );
        ResultSet rs = statement.executeQuery("select " +
                " from actions");
        List<Task> result = new ArrayList<Task>();
        while (rs.next()) {

        }
        return result;
    }
}
