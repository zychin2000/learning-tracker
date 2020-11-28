package sjsu.cs157a.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.dao.NoteDAO;
import sjsu.cs157a.model.Note;

/**
 *  This Servlet acts as a page controller for the application, it is for inserting a pic note
 */
@WebServlet("/dashboard/insertpic")
@MultipartConfig(maxFileSize = 16177215)  // upload file's size up to 16MB
public class InsertPicNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static NoteDAO noteDao;

	public void init() {
		noteDao = new NoteDAO(new DatabaseConnection());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/insertPic.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get value from text fields
		int class_id = Integer.parseInt(request.getParameter("class_id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String image_type = request.getParameter("image_type");
		String size = request.getParameter("size");

		InputStream input = null;
		Part filePart = request.getPart("photo");

		if (filePart != null) {
			System.out.println("Debug in picServlet: " + filePart.getContentType());

			// upload object
			input = filePart.getInputStream();
		}

		Note note = new Note(class_id, title, content, image_type, size, input);

		try {
			noteDao.InsertPicNote(note);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.sendRedirect("notelist");
	}

}
