package sjsu.cs157a.dao;

import org.json.JSONException;
import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.model.LearningPrinciple;
import sjsu.cs157a.model.Note;
import sjsu.cs157a.model.NoteComment;
import sjsu.cs157a.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentDAO implements DAOInterface<NoteComment>{
    DatabaseConnection databaseConnection;

    UserDAO userDAO;

    public CommentDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        userDAO = new UserDAO(databaseConnection);
    }

    @Override
    public boolean insert(NoteComment noteComment) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `project157a`.`comment` (`user_id`, `note_id`, `title`, `content`) VALUES (?,?,?,?);";

        databaseConnection.executeUpdate(sql, noteComment.getUser().getUserID(), String.valueOf(noteComment.getNote().getNote_id()),
                noteComment.getTitle(), noteComment.getContent());


        return false;
    }

    public List<NoteComment> listAllByNote(Note note) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM project157a.comment where note_id = ?;";

        List<Map<String, String>> result =  databaseConnection.executePreparedStatement(sql, String.valueOf(note.getNote_id()));

        List<NoteComment> noteCommentList = new ArrayList<>();

        for (Map<String, String> tuple : result) {
            noteCommentList.add(new NoteComment(Integer.parseInt(tuple.get("comment_id")),
                    tuple.get("title"), tuple.get("content"), note, userDAO.getById(tuple.get("user_id"))));

        }

        return noteCommentList;
    }

    @Override
    public List<NoteComment> listAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public NoteComment getById(String id) throws SQLException, ClassNotFoundException, JSONException {
        return null;
    }

    @Override
    public boolean update(NoteComment noteComment) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(NoteComment noteComment) {
        return false;
    }
}
