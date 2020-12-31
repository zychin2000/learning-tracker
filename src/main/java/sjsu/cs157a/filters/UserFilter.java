package sjsu.cs157a.filters;
import sjsu.cs157a.models.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/login.jsp";

        boolean loggedIn = session != null && session.getAttribute("user") != null;

        boolean loginRequest = request.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest) {
            filterChain.doFilter(request, response); // User is logged in, just continue request.
        } else {
            response.sendRedirect(loginURI); // Not logged in, show login page.
        }

    }

    @Override
    public void destroy() {

    }
}
