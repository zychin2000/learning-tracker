package sjsu.cs157a.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sjsu.cs157a.model.Note;

/**
 * This DAO class provides Create Read Update Delete database operation for the
 * note table
 * 
 * @author bellawei
 *
 */

public class NoteDao {

	private static String jdbcURL = "jdbc:mysql://localhost:3306/project157a?useSSL=false";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "Bella@1225";

	private static final String INSERT_NOTES_SQL = "INSERT INTO note_meta"
			+ " (principle_id, note_id, title, content) VALUES " + " (?, ?, ?,?);";

	private static final String SELECT_NOTES_BY_ID = "select principle_id, note_id, title, content from users where note_id =?";
	private static final String SELECT_ALL_NOTES = "select * from note_meta";
	private static final String DELETE_NOTES_SQL = "delete from note_meta where note_id = ?;";
	private static final String UPDATE_NOTES_SQL = "update note_meta set principle_id = ?, note_id= ?, title =?, content =? where note_id = ?;";

	public NoteDao() {

	}

	// connect to database
	protected static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	// Insert a note
	public void insertNote(Note note) throws SQLException {
		System.out.println(INSERT_NOTES_SQL);

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NOTES_SQL)) {
			preparedStatement.setInt(1, note.getPrinciple_id());
			preparedStatement.setInt(2, note.getNote_id());
			preparedStatement.setString(3, note.getTitle());
			preparedStatement.setString(4, note.getContent());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Select notes
	public Note selectNote(int id) {

		Note note = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTES_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {

				int principle_id = rs.getInt("principle_id");
				int note_id = rs.getInt("note_id");
				String title = rs.getString("title");
				String content = rs.getNString("content");

				note = new Note(principle_id, note_id, title, content);

			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return note;
	}
	
	public List<Note> selectAllNotes(){
		List<Note> nodeList = new ArrayList<>();
		
		   // Step1: Establishing a connection 
		try (Connection connection = getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NOTES);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int priciple_id = rs.getInt("principle_id");
				int note_id = rs.getInt("note_id");
				String title = rs.getString("title");
				String content = rs.getNString("content");
				nodeList.add(new Note(priciple_id,note_id,title,content));
			}
		} catch (SQLException e) {
			System.out.println(e);;
		}
		return nodeList;
	}
	
	// detele 
	
	public boolean deleteNote(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_NOTES_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	
	// update 
	public boolean updateNote(Note note) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_NOTES_SQL);) {
			statement.setInt(1, note.getPrinciple_id());
			statement.setInt(2, note.getNote_id());
			statement.setString(3, note.getTitle());
			statement.setString(4, note.getContent());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
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
	
	
	//-----------this class for testing---------------
	public static void main(String[] args) {
		List<Note> nodeList = new ArrayList<>();
		
		   // Step1: Establishing a connection 
		try (Connection connection = getConnection();

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NOTES);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int priciple_id = rs.getInt("principle_id");
				int note_id = rs.getInt("note_id");
				String title = rs.getString("title");
				String content = rs.getNString("content");
				nodeList.add(new Note(priciple_id,note_id,title,content));
			}
		} catch (SQLException e) {
			System.out.println(e);;
		}
		
		System.out.println(nodeList);
	}
	
	
	

}
