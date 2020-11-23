package sjsu.cs157a.servlets;


import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.dao.UserDAO;
import sjsu.cs157a.models.User;
import sjsu.cs157a.utils.PasswordUtils;
import sjsu.cs157a.utils.RegexUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ProfileServlet", urlPatterns ="/dashboard/profile")
public class ProfileServlet extends HttpServlet {

    UserDAO userDao;

    @Override
    public void init() {
        userDao = new UserDAO(new DatabaseConnection());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String uid = ((User)session.getAttribute("user")).getUserID();

       //get newest user information
        try {
            User user = userDao.getById(uid);
            req.setAttribute("user", user);


        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        req.getRequestDispatcher("/WEB-INF/jsp/userProfile.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String uid = ((User)session.getAttribute("user")).getUserID();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");

        try {
            User user = userDao.getById(uid);

            //validation
            //ensure email format is valid
            if(!RegexUtils.validateEmail(email))
                throw new UserProfileException("Invalid Email Format");

            //ensure there aren't similar emails in the database
            User emailCheck = userDao.getUserByCredentials(email);
            if (!email.equals(user.getEmail()) && emailCheck != null)
                throw new UserProfileException("Email is already used!");

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPhoneNo(phone);

            //only change the password if there is an etry
            if(!password.isEmpty()){
                //minimum password length
                if(password.length() < 6)
                    throw new UserProfileException("Password should be at least 6 characters!");

                //check if the two submitted passwords are equal
                if (!password.equals(confirmPassword))
                    throw new UserProfileException("Password does not match, please try again!");

                //hash the password
                PasswordUtils passwordUtils = new PasswordUtils();
                password = passwordUtils.hash(password.toCharArray());

                user.setPassword(password);
            }


            userDao.update(user);

            response.sendRedirect(request.getContextPath() + "/dashboard/profile?status=success");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException | UserProfileException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/userProfile.jsp").forward(request,response);
        }
    }
}

class UserProfileException extends Exception{
    public UserProfileException(String message) {
        super(message);
    }
}