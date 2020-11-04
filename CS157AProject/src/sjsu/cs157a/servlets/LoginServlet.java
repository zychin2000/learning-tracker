package sjsu.cs157a.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.dao.UserDAO;
import sjsu.cs157a.models.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	UserDAO userDao;
	
	public void init() {
		userDao = new UserDAO(new DatabaseConnection());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {

		        String email = request.getParameter("email");
		        String password = request.getParameter("password");

		        try {
					User user = userDao.getUserByCredentials(email,password);
		            if (user != null) {
		                HttpSession session = request.getSession();
		                session.setAttribute("userId",user.getUserID());
		                response.sendRedirect("dashboard.html");
		            } else {
		                request.setAttribute("error", "Incorrect login, please try again");
		                request.getRequestDispatcher("/login.html").forward(request,response);
		            }
		        } catch (ClassNotFoundException | SQLException e) {
		            e.printStackTrace();
		        }
		    }
	

}
