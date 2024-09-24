package com.wipro.controller;

import com.wipro.dao.EmployeeDAO;
import com.wipro.dao.EmployeeDAOImpl;
import com.wipro.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Employee employee = employeeDAO.getEmployee(id);
            request.setAttribute("employee", employee);
            request.getRequestDispatcher("/editEmployee.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            employeeDAO.deleteEmployee(id);
            response.sendRedirect("employees");
        } else {
            List<Employee> employees = employeeDAO.getAllEmployees();
            request.setAttribute("employees", employees);
            request.getRequestDispatcher("/listEmployees.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String name = request.getParameter("name");
            String position = request.getParameter("position");
            Employee newEmployee = new Employee(0, name, position);
            employeeDAO.addEmployee(newEmployee);
            response.sendRedirect("employees");
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String position = request.getParameter("position");
            Employee updatedEmployee = new Employee(id, name, position);
            employeeDAO.updateEmployee(updatedEmployee);
            response.sendRedirect("employees");
        }
    }
}
