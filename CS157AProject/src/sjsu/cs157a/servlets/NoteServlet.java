package sjsu.cs157a.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.dao.NoteDAO;
import sjsu.cs157a.model.Note;

/**
 * This Servlet acts as a page controller for the application, it is for displaying notes in the note page 
 * 
 * @author bellawei
 *
 */
@WebServlet( "/dashboard/notelist" )
public class NoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static NoteDAO noteDao; 

	public void init() {
		noteDao = new NoteDAO(new DatabaseConnection());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Note> listNote = null;
		try {
			listNote = noteDao.listAll();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("listNote", listNote);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/note.jsp");
		dispatcher.forward(request, response);
	}


	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		System.out.println("hello");
	}
}