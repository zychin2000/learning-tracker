package sjsu.cs157a.controller;

import sjsu.cs157a.models.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sjsu.cs157a.config.UserJdbc;

@WebServlet("/register")
//this servelet should be the profile page
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserJdbc UserDao;

	public void init() {

		UserDao = new UserJdbc();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.append(request.getSession().getAttribute("userId").toString());
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studentID = request.getParameter("StudentID");

		String firstName = request.getParameter("firstName");

		String lastName = request.getParameter("lastName");

		String phone = request.getParameter("phone");

		String email = request.getParameter("email");

		String password = request.getParameter("password");

//		User user = new User();
//
//		user.setUserID(studentID);
//
//		user.setFirstName(firstName);
//
//		user.setLastName(lastName);
//
//		user.setPhoneNo(phone);
//
//		user.setEmail(email);
//
//		user.setPassword(password);

//		try {
//
//			UserDao.registerUser(user);
//
//		} catch (Exception e) {
//
//			// TODO Auto-generated catch block
//
//			e.printStackTrace();
//
//		}

		response.sendRedirect("details.jsp");

	}

}
