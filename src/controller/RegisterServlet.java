package controller;

import dao.UserDAO;
import model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password");

            // Validation
            if (username.isEmpty() || password.length() < 6) {
                response.getWriter().println("Invalid input");
                return;
            }

            // Hash password
            String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());

            User user = new User();
            user.setUsername(username);
            user.setPassword(hashedPassword);

            UserDAO dao = new UserDAO();
            boolean created = dao.createUser(user);

            if (created) {
                response.sendRedirect("login.jsp");
            } else {
                response.getWriter().println("Failed to create user");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
