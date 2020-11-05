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

	private String jdbcURL = "jdbc:mysql://localhost:3306/project157a?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Bella@1225";

	private static final String INSERT_NOTES_SQL = "INSERT INTO note_meta"
			+ "  (principle_id, note_id, title, content) VALUES " + " (?, ?, ?,?);";

	private static final String SELECT_NOTES_BY_ID = "select principle_id, note_id, title, content from users where note_id =?";
	private static final String SELECT_ALL_NOTES = "select * from note_meta";
	private static final String DELETE_NOTES_SQL = "delete from note_meta where note_id = ?;";
	private static final String UPDATE_NOTES_SQL = "update users set principle_id = ?, note_id= ?, title =?, content =? where id = ?;";

	public NoteDao() {

	}

	// connect to database
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
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
			preparedStatement.setNString(4, note.getContent());
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

				int priciple_id = rs.getInt("principle_id");
				int note_id = rs.getInt("note_id");
				String title = rs.getString("title");
				String content = rs.getNString("content");

				note = new Note(priciple_id, note_id, title, content);

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
	// update 
	
	

}
