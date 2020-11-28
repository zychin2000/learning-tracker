package sjsu.cs157a.servlets;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.dao.NoteDAO;
import sjsu.cs157a.model.Note;

/**
 * This Servlet acts as a page controller for the application, it is for
 * inserting a note which contains a drop down menu for choose either upload a
 * doc or pic note
 * 
 * @author
 *
 */

@WebServlet("/dashboard/insert")
public class InsertNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static NoteDAO noteDao;

	public void init() {
		noteDao = new NoteDAO(new DatabaseConnection());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/insertNote.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("notelist");
	}

}
