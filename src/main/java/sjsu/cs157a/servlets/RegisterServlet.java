package sjsu.cs157a.servlets;

import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.dao.UserDAO;
import sjsu.cs157a.models.User;
import sjsu.cs157a.utils.PasswordUtils;
import sjsu.cs157a.utils.RegexUtils;

import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register.jsp")
public class RegisterServlet extends HttpServlet {

    UserDAO userDao;

    public void init() {
        userDao = new UserDAO(new DatabaseConnection());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");

        try {
            //ensure email format is valid
            if(!RegexUtils.validateEmail(email))
                throw new RegisterException("Invalid Email Format");

            //ensure there aren't similar emails in the database
            User user = userDao.getUserByCredentials(email);
            if (user != null)
                throw new RegisterException("User is already registered, please login!");

            //minimum password length
            if(password.length() < 6)
                throw new RegisterException("Password should be at least 6 characters!");

            //check if the two submitted passwords are equal
            if (!password.equals(confirmPassword))
                throw new RegisterException("Password does not match, please try again!");

            //hash the password
            PasswordUtils passwordUtils = new PasswordUtils();
            password = passwordUtils.hash(password.toCharArray());

            User newUser = new User(null, firstName, lastName, phone, email, password);
            userDao.insert(newUser);

            response.sendRedirect("dashboard/");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (RegisterException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
    }
}

class RegisterException extends Exception{
    public RegisterException(String message) {
        super(message);
    }
}