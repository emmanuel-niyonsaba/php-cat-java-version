package controller;

import dao.EmployeeDAO;
import model.Employee;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class UpdateEmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            EmployeeDAO dao = new EmployeeDAO();
            Employee emp = dao.findById(id);

            if (emp != null) {
                request.setAttribute("employee", emp);
                RequestDispatcher rd = request.getRequestDispatcher("update_employee.jsp");
                rd.forward(request, response);
            } else {
                response.getWriter().println("Employee not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("employee_name").trim();
            String email = request.getParameter("email").trim();
            String phone = request.getParameter("phone").trim();
            String position = request.getParameter("position").trim();
            String address = request.getParameter("address").trim();

            // ---------- Validation ----------
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                position.isEmpty() || address.isEmpty()) {
                response.getWriter().println("All fields are required");
                return;
            }
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                response.getWriter().println("Invalid email");
                return;
            }

            Employee emp = new Employee();
            emp.setId(id);
            emp.setEmployeeName(name);
            emp.setEmail(email);
            emp.setPhone(phone);
            emp.setPosition(position);
            emp.setAddress(address);

            EmployeeDAO dao = new EmployeeDAO();
            boolean updated = dao.updateEmployee(emp);

            if (updated) {
                response.sendRedirect("list_employees.jsp");
            } else {
                response.getWriter().println("Update failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
