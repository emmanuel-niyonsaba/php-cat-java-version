package dao;

import java.sql.*;
import model.User;
import util.DBConnection;

public class UserDAO {

    // Create User
    public boolean createUser(User user) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO users (username, passwrod) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        boolean inserted = ps.executeUpdate() > 0;
        conn.close();
        return inserted;
    }

    // Find User by username
    public User findByUsername(String username) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM users WHERE username=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        User user = null;
        if (rs.next()) {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("passwrod"));
            user.setRememberToken(rs.getString("remember_token"));
        }
        conn.close();
        return user;
    }

    // Update Remember Me Token
    public void updateRememberToken(int userId, String token) throws Exception {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE users SET remember_token=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, token);
        ps.setInt(2, userId);
        ps.executeUpdate();
        conn.close();
    }
}
