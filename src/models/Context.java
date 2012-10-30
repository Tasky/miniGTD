package models;

import util.DataLayer;
import util.exceptions.ConnectionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: nanne
 */
public class Context {
    private int id;
    private String name;

    public Context() {}
    public Context(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Context(String name) throws ConnectionException {
        PreparedStatement stat1 = null;
        try {
            Connection connection = DataLayer.getConnection();
            stat1 = connection.prepareStatement("select id, name from contexts where name = ?");
            stat1.setString(1, name);
            ResultSet result = stat1.executeQuery();
            if(result.next()) {
                fromResultSet(result);
            } else {
                PreparedStatement stat2 = connection.prepareStatement("INSERT INTO projects SET name = ?", Statement.RETURN_GENERATED_KEYS);
                stat2.setString(1, name);
                stat2.executeUpdate();
                ResultSet res = stat2.getGeneratedKeys();
                res.next();
                this.id = res.getInt(1);
                stat2.close();
            }
            stat1.close();
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage());
        }
    }

    public static List<Context> all() throws ConnectionException {
        List<Context> list = new ArrayList<Context>();

        try {
            PreparedStatement statement = DataLayer.getConnection().prepareStatement("SELECT id, name FROM contexts");
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
