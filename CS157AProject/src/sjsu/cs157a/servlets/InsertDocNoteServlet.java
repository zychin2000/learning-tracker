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
import sjsu.cs157a.model.Note;

/**
 * Servlet implementation class InsertDocNoteServlet
 */
@WebServlet("/dashboard/insertdoc")
public class InsertDocNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static NoteDAO noteDao;

	public void init() {
		noteDao = new NoteDAO(new DatabaseConnection());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/insertDoc.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int class_id = Integer.parseInt(request.getParameter("class_id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String text_font = request.getParameter("text_font");
		String file_type = request.getParameter("file_type");
		String docContent = request.getParameter("docContent");
		
		Note note = new Note(class_id,title,content,text_font,file_type,docContent);
		
		try {
			noteDao.insertDocNote(note);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.sendRedirect("notelist");
	}

}
