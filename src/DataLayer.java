import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Author: nanne
 */
public class DataLayer {
    public static Connection con;
    public static void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/minigtd",
                    "minigtd",
                    "laOaPnToLCMoAivB9IEhW0sdqK9TsK");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
