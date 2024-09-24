package com.wipro.dao;


import com.wipro.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(int id);
    Employee getEmployee(int id);
    List<Employee> getAllEmployees();
}
