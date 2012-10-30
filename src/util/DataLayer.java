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
                    //con = DriverManager.getConnection("jdbc:mysql://databases.aii.avans.nl/tslot_db",
                    //        "tslot",
                    //        "b8I54LMZ");
                    con = DriverManager.getConnection("jdbc:mysql://87.117.229.130/minigtd_db", "minigtd", "SfNDBWHPpC6A2epY");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static void closeConnection() {
        try {
            if (con != null && con.isClosed()) {
                con.close();
            }
        } catch (SQLException ignored) { }
    }
}
