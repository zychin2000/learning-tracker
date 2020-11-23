package sjsu.cs157a.servlets;


import sjsu.cs157a.config.DatabaseConnection;
import sjsu.cs157a.dao.UserDAO;
import sjsu.cs157a.models.User;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
