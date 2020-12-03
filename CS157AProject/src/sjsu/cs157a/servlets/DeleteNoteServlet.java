package sjsu.cs157a.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.dao.NoteDAO;

/**
 * This Servlet acts as a page controller for the application, it is for deletion 
 * @author 
 *
 */
@WebServlet("/dashboard/delete")
public class DeleteNoteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static NoteDAO noteDao;

	public void init() {
		noteDao = new NoteDAO(new DatabaseConnection());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		int id = Integer.parseInt(request.getParameter("noteId"));
		String id = request.getParameter("noteId");

		try {
			noteDao.deleteNote(id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.sendRedirect("notelist");

	}

	public static void main(String[] args) {

	}

}
