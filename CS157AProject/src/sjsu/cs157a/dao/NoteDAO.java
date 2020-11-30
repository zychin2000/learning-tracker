package sjsu.cs157a.dao;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.model.DocumentNote;
import sjsu.cs157a.model.Note;
import sjsu.cs157a.model.PictureNote;
import sjsu.cs157a.models.User;
import sjsu.cs157a.servlets.InsertPicNoteServlet;
import javax.swing.text.Document;

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

	/**
	 * Insert a note into note_docu table
	 */
	@Override
	public boolean insert(Note t) throws SQLException, ClassNotFoundException {
		String INSERT_NOTES = "INSERT INTO note_meta" + "(class_id,title,content) VALUES" + "(?,?,?);";
		String INSERT_NOTES_SQL = "INSERT INTO note_docu" + "(note_id, text_font, file_type) VALUES"
				+ "(LAST_INSERT_ID(), ?, ?);";

		System.out.println("Debug in NoteDao INSERT_NOTES_SQL: " + INSERT_NOTES_SQL);
		databaseConnection.executeUpdate(INSERT_NOTES, Integer.toString(t.getClass_id()), t.getTitle(), t.getContent());
		databaseConnection.executeUpdate(INSERT_NOTES_SQL, Integer.toString(t.getNote_id()), t.getText_font(),
				t.getFile_type());
		return true;
	}

	// Insert a doc Note to note_docu table
	public void insertDocNote(Note note) throws SQLException, ClassNotFoundException {
		String INSERT_NOTES = "INSERT INTO note_meta" + "(class_id,note_type, title,content) VALUES" + "(?,?,?,?);";
//		String INSERT_NOTES_SQL = "INSERT INTO note_docu" + "(note_id,text_font, file_type,content) VALUES"
//				+ "(LAST_INSERT_ID(),?, ?,?);";
		
		String INSERT_NOTES_SQL = "INSERT INTO note_docu" + "(note_id) VALUES"
		+ "(LAST_INSERT_ID());";

		try (Connection connection = getConnection();) {
			// commit all or roll back all, if any errors
			connection.setAutoCommit(false);

			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NOTES);
			PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_NOTES_SQL);

			preparedStatement.setInt(1, note.getClass_id());
			preparedStatement.setString(2, note.getNote_type());
			System.out.println("debug in notedao:" + note.getNote_type());
			preparedStatement.setString(3, note.getTitle());
			preparedStatement.setString(4, note.getContent());
			preparedStatement.addBatch();
			preparedStatement.execute();

//			preparedStatement1.setString(1, note.getText_font());
//			System.out.println("debug " + note.getText_font());
//			preparedStatement1.setString(2, note.getFile_type());
//			preparedStatement1.setString(3, note.getDocContent());

			System.out.println("debug in noteDao    " + preparedStatement1);
			// preparedStatement1.executeUpdate();

			preparedStatement1.execute();
			connection.commit();

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
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NOTES);
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

		} catch (Exception e) {
			// TODO: handle exception
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

				//use the orignal api to get the picture blob
				PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT link FROM project157a.note_picture WHERE note_id = ?");
				preparedStatement.setString(1, data.get("note_id"));

				ResultSet rs = preparedStatement.executeQuery();
				rs.next();

				Blob pictureFile = rs.getBlob("link");

				return new PictureNote(Integer.parseInt(note_meta_data.get("class_id")),"picture",note_meta_data.get("title"),note_meta_data.get("content"),data.get("image_type"),
						data.get("size"), pictureFile.getBinaryStream());
			}



		}
		return null;
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

		if(note instanceof DocumentNote){
			DocumentNote documentNote = (DocumentNote) note;
			String UPDATE_DOC_NOTE_SQL = "update note_docu set content = ? where note_id = ?;";
			databaseConnection.executeUpdate(UPDATE_DOC_NOTE_SQL, documentNote.getDocumentContent().toString(), Integer.toString(note.getNote_id()));
		}


		return rowUpdated;
	}

	// Delete a note
	public boolean deleteNote(int id) throws SQLException, ClassNotFoundException {
		String DELETE_NOTES_SQL = "delete from note_meta where note_id = ?;";
		boolean rowDeleted;
		connection = getConnection();
		try (PreparedStatement statement = connection.prepareStatement(DELETE_NOTES_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	@Override
	public boolean delete(Note t) {
		// TODO Auto-generated method stub
		return false;
	}

	
	// --------------- This main class for testing ----------
	public static void main(String args[]) throws ClassNotFoundException, SQLException {

		List<Note> nodeList = new ArrayList<>();
		connection = getConnection();
		try (PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM project157a.note_meta");) {
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String title = rs.getString("title");
				String content = rs.getNString("content");
				nodeList.add(new Note(title, content));
			}

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);

		}

		System.out.println("debug in main:" + nodeList);
	}

}