package sjsu.cs157a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * This class for connect to database 
 * @author bellawei
 *
 */
public class UserJdbc {
	 public int registerUser(User employee) throws ClassNotFoundException {
	        String INSERT_USERS_SQL = "INSERT INTO employee" +
	            "  ( first_name, last_name, username, password, address, contact) VALUES " +
	            " ( ?, ?, ?, ?,?, ?);";

	        int result = 0;
	     //   int i = 0; 

	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/project157a?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "Bella@1225");

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
	          //  preparedStatement.setInt(1, i);
	            preparedStatement.setString(1, employee.getFirstName());
	            preparedStatement.setString(2, employee.getLastName());
	            preparedStatement.setString(3, employee.getStudentID());
	            preparedStatement.setString(4, employee.getPhoneNo());
	            preparedStatement.setString(5, employee.getEmail());
	            preparedStatement.setString(6, employee.getPassword());
	            
	       //     i++; 
	            

	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            result = preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            // process sql exception
	            printSQLException(e);
	        }
	        return result;
	    }

	    private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }

}
