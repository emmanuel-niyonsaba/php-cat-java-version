// Update employee

public boolean updateEmployee(Employee emp) throws Exception {
    Connection conn = DBConnection.getConnection();
    String sql = "UPDATE employees SET employee_name=?, email=?, phone=?, position=?, address=? WHERE id=?";
    PreparedStatement ps = conn.prepareStatement(sql);

    ps.setString(1, emp.getEmployeeName());
    ps.setString(2, emp.getEmail());
    ps.setString(3, emp.getPhone());
    ps.setString(4, emp.getPosition());
    ps.setString(5, emp.getAddress());
    ps.setInt(6, emp.getId());

    boolean updated = ps.executeUpdate() > 0;
    conn.close();
    return updated;
}
