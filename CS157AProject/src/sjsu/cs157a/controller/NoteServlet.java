package sjsu.cs157a.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sjsu.cs157a.config.NoteDao;
import sjsu.cs157a.model.Note;

/**
 * This Servlet acts as a page controller for the application, handing all
 * request from the user
 * 
 * @author bellawei
 *
 */

@WebServlet("/")
public class NoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private NoteDao noteDao;

	public void init() {
		noteDao = new NoteDao();

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
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertNote(request, response);
				break;
			case "/delete":
				deleteNote(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
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
		}

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("addnote.jsp");
		dispatcher.forward(request, response);
	}

	private void listNote(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Note> listNote = noteDao.selectAllNotes();
		request.setAttribute("listNote", listNote);
		RequestDispatcher dispatcher = request.getRequestDispatcher("note.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("note_id"));
		Note existingNote = noteDao.selectNote(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("addnote.jsp");
		request.setAttribute("note", existingNote);
		dispatcher.forward(request, response);

	}

	private void insertNote(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, NumberFormatException {

		int principle_id = Integer.parseInt(request.getParameter("principle_id"));
		int note_id = Integer.parseInt(request.getParameter("note_id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		System.out.println("Debug --> Note Servlet: " + principle_id);

		Note note = new Note(principle_id, note_id, title, content);
		noteDao.insertNote(note);
		response.sendRedirect("list");

	}

	private void deleteNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("note_id"));
		noteDao.deleteNote(id);
		response.sendRedirect("list");

	}

	private void updateNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int principle_id = Integer.parseInt(request.getParameter("principle_id"));
		int note_id = Integer.parseInt(request.getParameter("note_id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		Note note = new Note(principle_id, note_id, title, content);
		noteDao.updateNote(note);
		response.sendRedirect("list");

	}

}
