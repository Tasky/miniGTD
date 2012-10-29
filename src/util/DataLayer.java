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
        try {
            if (con == null || con.isClosed()) {
                    con = DriverManager.getConnection("jdbc:mysql://databases.aii.avans.nl/tslot_db",
                            "tslot",
                            "b8I54LMZ");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
