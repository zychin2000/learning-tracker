package sjsu.cs157a.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.dao.UserDAO;
import sjsu.cs157a.models.User;
import sjsu.cs157a.utils.PasswordUtils;

@WebServlet(name = "LoginServlet", urlPatterns ="/login.jsp")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    UserDAO userDao;

    public void init() {
        userDao = new UserDAO(new DatabaseConnection());
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userDao.getUserByCredentials(email);
            if (user == null)
                throw new AuthenticationException();

            PasswordUtils passwordUtils = new PasswordUtils();
            boolean auth = passwordUtils.authenticate(password.toCharArray(), user.getPassword());

            if(!auth)
                throw new AuthenticationException();

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            System.out.println("Debug in Login Servlet: " + user.getFirstName()+" " + user.getLastName());
            response.sendRedirect("dashboard/");
        } catch (AuthenticationException e) {
            request.setAttribute("error", "Incorrect login, please try again");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


}
