package sjsu.cs157a.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.model.*;
import sjsu.cs157a.models.User;

/**
 * 
 * @author
 *
 */
public class NoteDAO implements DAOInterface<Note> {

	private static DatabaseConnection databaseConnection = new DatabaseConnection();
	private static Connection connection;

	public NoteDAO(DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

	protected static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection connection = databaseConnection.initializeConnection();
		return connection;

	}


	@Override
	public boolean insert(Note t) throws SQLException, ClassNotFoundException {
		return true;
	}

	public int insertUserNoteConnection(Note note, User user) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO `uploads` (`user_id`, `note_id`) VALUES (?, ?);";
		return databaseConnection.executeUpdate(sql, user.getUserID(), String.valueOf(note.getNote_id()));

	}

	// Insert a doc Note to note_docu table
	public void insertDocNote(Note note) throws SQLException, ClassNotFoundException {
		String INSERT_NOTES = "INSERT INTO note_meta" + "(class_id,note_type, title,content) VALUES" + "(?,?,?,?);";

		String INSERT_NOTES_SQL = "INSERT INTO note_docu" + "(note_id) VALUES" + "(LAST_INSERT_ID());";

		try (Connection connection = getConnection();) {
			// commit all or roll back all, if any errors
			connection.setAutoCommit(false);

			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NOTES,
					Statement.RETURN_GENERATED_KEYS);
			PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_NOTES_SQL);

			preparedStatement.setInt(1, note.getClass_id());
			preparedStatement.setString(2, note.getNote_type());
			preparedStatement.setString(3, note.getTitle());
			preparedStatement.setString(4, note.getContent());
			preparedStatement.addBatch();
			preparedStatement.execute();
			preparedStatement1.execute();
			connection.commit();

			// bind the generated keys back to the model
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					note.setNote_id(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Creating note failed, no ID obtained.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Insert a doc Note to note_picture table
	public void InsertPicNote(Note note) throws SQLException, ClassNotFoundException {
		String INSERT_NOTES = "INSERT INTO note_meta" + "(class_id,note_type, title,content) VALUES" + "(?,?,?,?);";
		String INSERT_PIC_NOTE = "INSERT INTO note_picture" + "(note_id, image_type, size, link) VALUES"
				+ "(LAST_INSERT_ID(), ?, ?,?);";

		try (Connection connection = getConnection();) {
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NOTES,
					Statement.RETURN_GENERATED_KEYS);
			PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_PIC_NOTE);

			preparedStatement.setInt(1, note.getClass_id());
			preparedStatement.setString(2, note.getNote_type());
			preparedStatement.setString(3, note.getTitle());
			preparedStatement.setString(4, note.getContent());
			preparedStatement.addBatch();
			preparedStatement.execute();

			preparedStatement1.setString(1, note.getImage_type());
			preparedStatement1.setString(2, note.getSize());
			preparedStatement1.setBlob(3, note.getInputStream());
			System.out.println("Debug in NoteDao: " + note.getImage_type() + " " + note.getInputStream());

			preparedStatement1.execute();
			connection.commit();

			// bind the generated keys back to the model
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					note.setNote_id(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Creating note failed, no ID obtained.");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Override
	public List<Note> listAll() throws SQLException, ClassNotFoundException {
		// Create a list to hold notes
		List<Note> noteList = new ArrayList<>();

		// Step 1: Establishing a connection
		connection = getConnection();
		try (PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM project157a.note_meta");) {

			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("note_id");
				String title = rs.getString("title");
				String content = rs.getNString("content");
				noteList.add(new Note(id, title, content));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);

		}
		return noteList;

	}

	public List<Note> listAllByUser(User user) throws SQLException, ClassNotFoundException {
		String sql = "SELECT note_meta.note_id, note_meta.class_id, title, content, note_type \n"
				+ "FROM project157a.note_meta INNER JOIN uploads ON note_meta.note_id = uploads.note_id\n"
				+ "WHERE user_id = ?\n";
		List<Map<String, String>> result = databaseConnection.executePreparedStatement(sql, user.getUserID());

		List<Note> noteList = new ArrayList<>();

		for (Map<String, String> tuple : result) {
			noteList.add(new Note(Integer.parseInt(tuple.get("note_id")), tuple.get("title"), tuple.get("content")));

		}

		return noteList;
	}

	@Override
	public Note getById(String id) throws SQLException, ClassNotFoundException, JSONException {
		// check for the type of note first
		String sql = "SELECT * FROM project157a.note_meta WHERE note_id = ?";

		List<Map<String, String>> note_meta_result = databaseConnection.executePreparedStatement(sql, id);

		if (note_meta_result.isEmpty())
			return null;

		Map<String, String> note_meta_data = note_meta_result.get(0);

		// the note type is document
		if (note_meta_data.get("note_type").equals("docu")) {
			sql = "SELECT * FROM project157a.note_docu WHERE note_id = ?";
			List<Map<String, String>> note_document_result = databaseConnection.executePreparedStatement(sql,
					note_meta_data.get("note_id"));

			if (!note_document_result.isEmpty()) {
				Map<String, String> data = note_document_result.get(0);
				return new DocumentNote(Integer.parseInt(data.get("note_id")), note_meta_data.get("title"),
						note_meta_data.get("content"), data.get("content"));
			}

		}

		if (note_meta_data.get("note_type").equals("picture")) {

			// todo retrieve picture note
			System.out.println("It is a picture");

			sql = "SELECT * FROM project157a.note_picture WHERE note_id = ?";
			List<Map<String, String>> note_picture_result = databaseConnection.executePreparedStatement(sql,
					note_meta_data.get("note_id"));

			if (!note_picture_result.isEmpty()) {
				Map<String, String> data = note_picture_result.get(0);

				// use the orignal api to get the picture blob
				PreparedStatement preparedStatement = getConnection()
						.prepareStatement("SELECT link FROM project157a.note_picture WHERE note_id = ?");
				preparedStatement.setString(1, data.get("note_id"));

				ResultSet rs = preparedStatement.executeQuery();
				rs.next();

				Blob pictureFile = rs.getBlob("link");

				return new PictureNote(Integer.parseInt(note_meta_data.get("class_id")),
						Integer.parseInt(note_meta_data.get("note_id")), "picture", note_meta_data.get("title"),
						note_meta_data.get("content"), data.get("image_type"), data.get("size"),
						pictureFile.getBinaryStream());
			}

		}
		return new Note(Integer.parseInt(note_meta_data.get("note_id")), note_meta_data.get("title"),
				note_meta_data.get("content"));
	}

	// Select notes
	public Note selectNote(int id) throws ClassNotFoundException, SQLException {
		String SELECT_NOTES_BY_ID = "select note_id, title, content from users where note_id =?";
		Note note = null;
		// Step 1: Establishing a Connection
		connection = getConnection();

		try (
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTES_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int note_id = rs.getInt("note_id");
				String title = rs.getString("title");
				String content = rs.getNString("content");
				note = new Note(note_id, title, content);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return note;
	}

	@Override
	public boolean update(Note note) throws SQLException, ClassNotFoundException {
		String UPDATE_NOTES_SQL = "update note_meta set title =?, content =? where note_id = ?;";
		boolean rowUpdated;
		connection = getConnection();

		try (PreparedStatement statement = connection.prepareStatement(UPDATE_NOTES_SQL);) {
			statement.setInt(3, note.getNote_id());
			statement.setString(1, note.getTitle());
			statement.setString(2, note.getContent());
			rowUpdated = statement.executeUpdate() > 0;
		}

		if (note instanceof DocumentNote) {
			DocumentNote documentNote = (DocumentNote) note;
			String UPDATE_DOC_NOTE_SQL = "update note_docu set content = ? where note_id = ?;";
			databaseConnection.executeUpdate(UPDATE_DOC_NOTE_SQL, documentNote.getDocumentContent().toString(),
					Integer.toString(note.getNote_id()));
		}

		return rowUpdated;
	}

	// Delete a note
	public boolean deleteNote(String id) throws SQLException, ClassNotFoundException {
		String DELETE_NOTES_SQL = "SELECT * FROM project157a.note_meta WHERE note_id = ?";

		List<Map<String, String>> note_meta_result = databaseConnection.executePreparedStatement(DELETE_NOTES_SQL, id);

		if (note_meta_result.isEmpty())
			return false;

		Map<String, String> note_meta_data = note_meta_result.get(0);

		boolean rowDeleted = false;

		if (note_meta_data.get("note_type").equals("docu")) {

			String DELETE_DOCU_SQL = "DELETE note_meta, note_docu\n" + "FROM note_meta\n"
					+ "LEFT JOIN note_docu ON note_meta.note_id = note_docu.note_id \n"
					+ "where note_meta.note_id = ?;";

			databaseConnection.executeUpdate(DELETE_DOCU_SQL, note_meta_data.get("note_id"));

			rowDeleted = true;

		} 
		
		if (note_meta_data.get("note_type").equals("picture")) {

			String DELETE_PIC_SQL = 
					"DELETE note_meta, note_picture\n" + 
					"FROM note_meta\n" + 
					"LEFT JOIN note_picture ON note_meta.note_id = note_picture.note_id \n" + 
					"where note_meta.note_id = ?;";

			databaseConnection.executeUpdate(DELETE_PIC_SQL, note_meta_data.get("note_id"));
			rowDeleted = true;
		}

		return rowDeleted;
	}

	@Override
	public boolean delete(Note t) {
		return false;
	}

}