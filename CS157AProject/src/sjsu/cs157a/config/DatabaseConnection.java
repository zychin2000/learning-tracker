package sjsu.cs157a.config;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Database connection class
 *
 */

public class DatabaseConnection {

    /**
     * @param query The SQL Query, an SQL statement to be sent to the database, typically a static SQL SELECT statement
     * @param values Optional - Any question marks in the query string would be replaced with the values specified by the order.
     * @return A list of tuples in the form of key value pairs of Column Name and value
     * @throws SQLException Any error in the SQL code will be thrown
     * @throws ClassNotFoundException
     */
    public static List<Map<String, String>> executePreparedStatement(String query, String... values) throws SQLException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            //Initialize the database connection
            con = DatabaseConnection.initializeDatabase();

            preparedStatement = con.prepareStatement(query);

            //manually insert the values into the preparedStatement
            for (int i = 1; i <= values.length; i++) {
                preparedStatement.setString(i, values[i - 1]);
            }

            ResultSet rs = preparedStatement.executeQuery();

            //After getting the result set, transform the structure into List of Map
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            ResultSetMetaData meta = rs.getMetaData();
            while (rs.next()) {
                Map<String, String> map = new HashMap<>();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    String key = meta.getColumnName(i);
                    String value = rs.getString(key);
                    map.put(key, value);
                }
                list.add(map);
            }

            return list;
        } finally {
            //close the connections
            if (con != null) con.close();
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    /**
     * @param query an SQL Data Manipulation Language (DML) statement, such as INSERT, UPDATE or DELETE; or an SQL statement that returns nothing, such as a DDL statement.
     * @param values Optional - Any question marks in the query string would be replaced with the values specified by the order.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static int executeUpdate(String query, String... values) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            //Initialize the database connection
            con = DatabaseConnection.initializeDatabase();

            preparedStatement = con.prepareStatement(query);

            //manually insert the values into the preparedStatement
            for (int i = 1; i <= values.length; i++) {
                preparedStatement.setString(i, values[i - 1]);
            }

            return preparedStatement.executeUpdate();
        } finally {
            //close the connections
            if (con != null) con.close();
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    //initializes the database connection
    private static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
        // Initialize database information
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql:// localhost:3306/";
        // Database name to access
        String dbName = "cs157a";
        String miscSetting = "?serverTimezone=Asia/Kuala_Lumpur";
        String dbUsername = "root";
        String dbPassword = "admin1";

        Class.forName(dbDriver);

        Connection con = DriverManager.getConnection(dbURL + dbName + miscSetting,
                dbUsername,
                dbPassword);

        return con;
    }


    //example for runnning
    public static void main(String args[]) {
        try {
            //sql update testing
            int id = 42;

            executeUpdate("INSERT INTO `cs157a`.`hw1` (`SJSU_ID`, `name`, `major`) VALUES (?, 'test', 'hi');", Integer.toString(id));

            //sql query testing
            List<Map<String, String>> rs = executePreparedStatement("select * from cs157a.hw1 where SJSU_ID = ?", "014562795");
            for (Map<String, String> m : rs) {
                for (Map.Entry<String, String> e : m.entrySet()) {
                    String key = e.getKey();
                    String value = e.getValue();
                    System.out.print(value + " ");
                }
                System.out.println();
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

}
