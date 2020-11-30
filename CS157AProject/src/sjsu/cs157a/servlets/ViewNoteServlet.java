package sjsu.cs157a.servlets;

import org.json.JSONException;
import org.json.JSONObject;
import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.dao.LearningPrincipleDAO;
import sjsu.cs157a.dao.NoteDAO;
import sjsu.cs157a.model.DocumentNote;
import sjsu.cs157a.model.LearningPrinciple;
import sjsu.cs157a.model.Note;
import sjsu.cs157a.model.NoteLearningPrinciple;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/dashboard/note")
public class ViewNoteServlet extends HttpServlet {


    private static NoteDAO noteDao;
    private static LearningPrincipleDAO learningPrincipleDAO;

    public void init() {
        DatabaseConnection dbC = new DatabaseConnection();
        noteDao = new NoteDAO(dbC);
        learningPrincipleDAO = new LearningPrincipleDAO(dbC);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String note_id = request.getParameter("id");
        System.out.println("debug in viewNoteservle: " + note_id);

        try {
            Note note = noteDao.getById(note_id);

            Set<LearningPrinciple> learningPrinciples = new HashSet<>();

            //iterate through all the cycles at the learning principles ENUM
            for(LearningPrinciple.CYCLE cycle: LearningPrinciple.CYCLE.values()){
                List<LearningPrinciple> learningPrincipleList = learningPrincipleDAO.listByCycle(cycle);
                List<NoteLearningPrinciple> noteLearningPrincipleList = learningPrincipleDAO.getLearningPrincipleByNoteIdAndCycle(Integer.parseInt(note_id), cycle);

                //get the count of note learning principles that are alread
                int lpDoneCount = learningPrincipleDAO.getLearningPrincipleDoneCount(Integer.parseInt(note_id), cycle);

                //if the note hasn't fulfilled all the learning principles in a given cycle, exit loop and use this cycle iteration
                if(lpDoneCount < learningPrincipleList.size()){
                    //add the learningPrincipleList to get all of the learning principles first, then add the learningPrinciples which are already affected
                    learningPrinciples.addAll(noteLearningPrincipleList);
                    learningPrinciples.addAll(learningPrincipleList);

                    break;
                }

            }

            if (note instanceof DocumentNote) {
                request.setAttribute("note", note);
                request.setAttribute("learningPrinciples", learningPrinciples);
                request.getRequestDispatcher("/WEB-INF/jsp/noteDocument.jsp").forward(request, response);
                return;
            }

        } catch (SQLException | JSONException | ClassNotFoundException throwables) {
            response.sendError(500);
            throwables.printStackTrace();
        }


        //if no note can be found, redirect to note listing instead
        response.sendRedirect("notelist");
    }

    @Override
    //for creating
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String note_id = req.getParameter("note_id");
        String content = req.getParameter("content");

        try {
            Note note = noteDao.getById(note_id);

            if (note instanceof DocumentNote) {

                DocumentNote documentNote = (DocumentNote) note;

                if (content != null)
                    documentNote.setDocumentContent(new JSONObject(content));

                noteDao.update(documentNote);

            }

        } catch (SQLException | JSONException | ClassNotFoundException throwables) {
            resp.sendError(500);
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
