package sjsu.cs157a.servlets;

import org.json.JSONException;
import org.json.JSONObject;
import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.dao.CommentDAO;
import sjsu.cs157a.dao.LearningPrincipleDAO;
import sjsu.cs157a.dao.NoteDAO;
import sjsu.cs157a.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/dashboard/note")
public class ViewNoteServlet extends HttpServlet {


    private static NoteDAO noteDao;
    private static LearningPrincipleDAO learningPrincipleDAO;
    private static CommentDAO commentDAO;

    public void init() {
        DatabaseConnection dbC = new DatabaseConnection();
        noteDao = new NoteDAO(dbC);
        learningPrincipleDAO = new LearningPrincipleDAO(dbC);
        commentDAO = new CommentDAO(dbC);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String note_id = request.getParameter("id");
        System.out.println("debug in viewNoteservle: " + note_id);

        try {
            Note note = noteDao.getById(note_id);

            List<NoteComment> noteCommentList = commentDAO.listAllByNote(note);

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
                request.setAttribute("comments", noteCommentList);
                request.getRequestDispatcher("/WEB-INF/jsp/noteDocument.jsp").forward(request, response);
                return;
            }

            if(note instanceof PictureNote){

                //decode the picture
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = note.getInputStream().read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);


                note.getInputStream().close();
                outputStream.close();

                System.out.println(base64Image);

                request.setAttribute("note", note);
                request.setAttribute("base64pic", base64Image);
                request.setAttribute("learningPrinciples", learningPrinciples);
                request.setAttribute("comments", noteCommentList);
                request.getRequestDispatcher("/WEB-INF/jsp/notePicture.jsp").forward(request, response);
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
