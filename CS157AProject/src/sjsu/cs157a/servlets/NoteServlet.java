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
 * This Servlet acts as a page controller for the application, handing all
 * request from the user
 * 
 * @author bellawei
 *
 */
@WebServlet( "/notelist" )
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
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/notelist/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertNote(request, response);
				break;
			case "/delete":
				deleteNote(request, response);
				break;
			case "/edit":
				showNewForm(request, response);
				break;
			case "/update":
				updateNote(request, response);
				break;
			default:
				listNote(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void listNote(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		List<Note> listNote = noteDao.listAll();
		request.setAttribute("listNote", listNote);
		RequestDispatcher dispatcher = request.getRequestDispatcher("note.jsp");
		dispatcher.forward(request, response);

	}

	// TODO: update a note
	private void updateNote(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		int note_id = Integer.parseInt(request.getParameter("note_id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		Note note = new Note(note_id, title, content);
		noteDao.updateNote(note);
		response.sendRedirect("notelist");
		
	}

	// TODO: detele a note 
	private void deleteNote(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("note_id"));
		noteDao.deleteNote(id);
		response.sendRedirect("notelist");

	}

	// TODO: insert value-- > Now issue: insert values become null  
	private void insertNote(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		String title = request.getParameter("notetitle");
		String content = request.getParameter("content");
		Note note = new Note(title, content);
		noteDao.insertNote(note);
		
		try {
			response.sendRedirect("notelist");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("addNote.jsp");
		dispatcher.forward(request, response);

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		System.out.println("hello");
	}
}
