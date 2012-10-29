package models;

import util.DataLayer;
import util.exceptions.ConnectionException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: nanne
 */
public class Context {
    private int id;
    private String name;

    public static List<Context> all() throws ConnectionException {
        List<Context> list = new ArrayList<Context>();

        try {
            PreparedStatement statement = DataLayer.getConnection().prepareStatement("SELECT id, name FROM statuses");
            statement.execute();
            ResultSet res = statement.getResultSet();

            while(res.next()) {
                Context context = new Context();
                context.fromResultSet(res);
                list.add(context);
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
