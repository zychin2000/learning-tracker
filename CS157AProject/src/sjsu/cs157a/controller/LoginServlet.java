package sjsu.cs157a.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sjsu.cs157a.config.UserLoginDao;
import sjsu.cs157a.model.UserLogin;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserLoginDao loginDao;
	
	public void init() {
		loginDao = new UserLoginDao();
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {

		        String studentID = request.getParameter("studentID");
		        String password = request.getParameter("password");
		        UserLogin loginBean = new UserLogin();
		        loginBean.setStudentID(studentID);
		        loginBean.setPassword(password);

		        try {
		            if (loginDao.validate(loginBean)) {
		                //HttpSession session = request.getSession();
		                // session.setAttribute("username",username);
		                response.sendRedirect("registration.html");
		            } else {
		                HttpSession session = request.getSession();
		                //session.setAttribute("user", username);
		                //response.sendRedirect("login.jsp");
		            }
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        }
		    }
	

}
