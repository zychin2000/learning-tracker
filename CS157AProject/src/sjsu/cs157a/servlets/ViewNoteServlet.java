package sjsu.cs157a.servlets;

import org.json.JSONException;
import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.dao.NoteDAO;
import sjsu.cs157a.model.DocumentNote;
import sjsu.cs157a.model.Note;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet( "/dashboard/note" )
public class ViewNoteServlet extends HttpServlet {


    private static NoteDAO noteDao;

    public void init() {
        noteDao = new NoteDAO(new DatabaseConnection());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String note_id = request.getParameter("id");

        try {
           Note note = noteDao.getById(note_id);

           if(note != null  && note instanceof DocumentNote) {
               request.setAttribute("note", note);
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
        super.doPost(req, resp);
    }

    @Override
    //for updating
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String note_id = req.getParameter("id");
        String content = req.getParameter("content");

        try {
            Note note = noteDao.getById(note_id);

            if(note != null  && note instanceof DocumentNote) {
                req.setAttribute("note", note);
                req.getRequestDispatcher("/WEB-INF/jsp/noteDocument.jsp").forward(req, resp);
                return;
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
