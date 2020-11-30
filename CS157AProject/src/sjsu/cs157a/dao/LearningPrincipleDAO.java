package sjsu.cs157a.dao;

import org.json.JSONException;
import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.model.LearningPrinciple;
import sjsu.cs157a.model.Note;
import sjsu.cs157a.model.NoteLearningPrinciple;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LearningPrincipleDAO implements DAOInterface<LearningPrinciple> {
    DatabaseConnection databaseConnection;

    public LearningPrincipleDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public List<NoteLearningPrinciple> getLearningPrincipleByNoteIdAndCycle(int noteId, LearningPrinciple.CYCLE cycle) throws SQLException, ClassNotFoundException {
        String sql = "SELECT learning_principle.principle_id, method, cycle, status, description " +
                "FROM project157a.learning_principle INNER JOIN note_learningprogress ON learning_principle.principle_id = note_learningprogress.principle_id " +
                "WHERE note_id = ? and cycle = ?";
        List<Map<String, String>> result = databaseConnection.executePreparedStatement(sql, Integer.toString(noteId), cycle.toString());

        List<NoteLearningPrinciple> noteLearningPrincipleList = new ArrayList<>();

        for (Map<String, String> tuple : result) {
            noteLearningPrincipleList.add(new NoteLearningPrinciple(Integer.parseInt(tuple.get("principle_id")),
                    tuple.get("method"), LearningPrinciple.CYCLE.valueOf(tuple.get("cycle")), tuple.get("description"), tuple.get("status")));

        }

        return noteLearningPrincipleList;
    }

    public int getLearningPrincipleDoneCount(int noteId, LearningPrinciple.CYCLE cycle) throws SQLException, ClassNotFoundException {
        String sql = "SELECT count(*) " +
                "FROM project157a.note_learningprogress inner join learning_principle on learning_principle.principle_id = note_learningprogress.principle_id " +
                "where note_id = ? and cycle = ? and status = 'done'";
        List<Map<String, String>> result =  databaseConnection.executePreparedStatement(sql, String.valueOf(noteId), cycle.toString());

        return Integer.parseInt(result.get(0).get("count(*)"));
    }


    @Override
    public boolean insert(LearningPrinciple learningPrinciple) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<LearningPrinciple> listAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM learning_principle;";
        List<Map<String, String>> result = databaseConnection.executePreparedStatement(sql);
        return getLearningPrinciplesFromMap(result);
    }

    public List<LearningPrinciple> listByCycle(LearningPrinciple.CYCLE cycle) throws SQLException,ClassNotFoundException{
        String sql = "SELECT * FROM learning_principle WHERE cycle=?;";
        List<Map<String, String>> result = databaseConnection.executePreparedStatement(sql, cycle.toString());
        return getLearningPrinciplesFromMap(result);
    }

    private List<LearningPrinciple> getLearningPrinciplesFromMap(List<Map<String, String>> result) {
        List<LearningPrinciple> learningPrincipleList = new ArrayList<>();

        for (Map<String, String> tuple : result) {
            learningPrincipleList.add(new LearningPrinciple(Integer.parseInt(tuple.get("principle_id")),
                    tuple.get("method"), LearningPrinciple.CYCLE.valueOf(tuple.get("cycle")), tuple.get("description")));

        }
        return learningPrincipleList;
    }

    public boolean updateNoteLearningPrincipleStatus(LearningPrinciple learningPrinciple, Note note, String status) throws SQLException, ClassNotFoundException {
        String sql  = "REPLACE INTO note_learningprogress(principle_id, note_id, status) values (?,?,?)";
        databaseConnection.executeUpdate(sql, String.valueOf(learningPrinciple.getPrinciple_id()), String.valueOf(note.getNote_id()), status);
        return false;
    }

    @Override
    public LearningPrinciple getById(String id) throws SQLException, ClassNotFoundException, JSONException {
        String sql = "SELECT * FROM learning_principle WHERE principle_id=?;";
        List<Map<String, String>> result = databaseConnection.executePreparedStatement(sql, id);
        return getLearningPrinciplesFromMap(result).get(0);
    }

    @Override
    public boolean update(LearningPrinciple learningPrinciple) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(LearningPrinciple learningPrinciple) {
        return false;
    }
}
