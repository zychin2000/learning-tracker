package sjsu.cs157a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UserJdbcCreateTable {
	public static void main(String[] args) throws Exception {
		// Connection to MySql
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project157a?serverTimezone=UTC", "root", "Bella@1225");
		
		// Create register table
		String createSql = "CREATE TABLE user (sjsu_id VARCHAR(20) NOT NULL, " 
				+ "first_name VARCHAR(45) NOT NULL,"
				+ "last_name VARCHAR(45) NOT NULL,"
				+ "Phone VARCHAR(20) NULL,"
				+ "email VARCHAR(50) NULL,"
				+ "password VARCHAR(20) NOT NULL, "
				+ "PRIMARY KEY (sjsu_id))";
		Statement statement = connection.createStatement();
		statement.execute(createSql);
		statement.close();
		
		// Close connection
		connection.close();
	}
		
		

}
