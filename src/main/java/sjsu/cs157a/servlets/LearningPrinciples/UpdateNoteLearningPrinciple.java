package sjsu.cs157a.servlets.LearningPrinciples;

import org.json.JSONException;
import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.dao.LearningPrincipleDAO;
import sjsu.cs157a.dao.NoteDAO;
import sjsu.cs157a.model.LearningPrinciple;
import sjsu.cs157a.model.Note;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet(name = "UpdateNoteLearningPrinciple", urlPatterns ="/dashboard/note/updateLearningPrinciple")
public class UpdateNoteLearningPrinciple  extends HttpServlet {

    private static LearningPrincipleDAO learningPrincipleDAO;
    private static NoteDAO noteDao;

    public void init() {
        DatabaseConnection dbC = new DatabaseConnection();
        noteDao = new NoteDAO(dbC);
        learningPrincipleDAO = new LearningPrincipleDAO(dbC);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String note_id = req.getParameter("note_id");
        String principle_id = req.getParameter("principle_id");
        String status = req.getParameter("status");
        String url = req.getHeader("referer");

        String[] ACCEPTABLE_STATUSES = {"done", "inProgress"};

        try {
            Note note = noteDao.getById(note_id);
            LearningPrinciple learningPrinciple = learningPrincipleDAO.getById(principle_id);

            if(note!= null && status != null && Arrays.asList(ACCEPTABLE_STATUSES).contains(status))
                learningPrincipleDAO.updateNoteLearningPrincipleStatus(learningPrinciple, note, status);

        } catch (SQLException | ClassNotFoundException | JSONException throwables) {
            throwables.printStackTrace();
        }

        resp.sendRedirect(url);


    }
}
