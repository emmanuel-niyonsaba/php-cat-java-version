package controller;

import dao.UserDAO;
import model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.security.SecureRandom;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password");
            boolean remember = request.getParameter("remember") != null;

            if (username.isEmpty() || password.isEmpty()) {
                response.getWriter().println("Username and password required");
                return;
            }

            UserDAO dao = new UserDAO();
            User user = dao.findByUsername(username);

            if (user != null && org.mindrot.jbcrypt.BCrypt.checkpw(password, user.getPassword())) {

                HttpSession session = request.getSession();
                session.setAttribute("user_id", user.getId());
                session.setAttribute("username", user.getUsername());

                // Remember Me cookie
                if (remember) {
                    String token = generateToken();
                    Cookie cookie = new Cookie("remember_me", token);
                    cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);

                    dao.updateRememberToken(user.getId(), token);
                }

                response.sendRedirect("homepage.jsp");

            } else {
                response.getWriter().println("Invalid login");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateToken() {
        byte[] bytes = new byte[32];
        new SecureRandom().nextBytes(bytes);
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
