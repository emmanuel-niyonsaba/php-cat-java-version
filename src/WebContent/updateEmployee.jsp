<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Employee" %>
<%
    Employee emp = (Employee) request.getAttribute("employee");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
</head>
<body>
<h2>Update Employee</h2>

<form action="UpdateEmployeeServlet" method="post">
    <input type="hidden" name="id" value="<%= emp.getId() %>">
    <label>Name:</label>
    <input type="text" name="employee_name" value="<%= emp.getEmployeeName() %>" required><br>
    <label>Email:</label>
    <input type="email" name="email" value="<%= emp.getEmail() %>" required><br>
    <label>Phone:</label>
    <input type="text" name="phone" value="<%= emp.getPhone() %>" required><br>
    <label>Position:</label>
    <input type="text" name="position" value="<%= emp.getPosition() %>" required><br>
    <label>Address:</label>
    <textarea name="address" required><%= emp.getAddress() %></textarea><br>
    <button type="submit">Update</button>
</form>

<a href="list_employees.jsp">Back to Employee List</a>
</body>
</html>
