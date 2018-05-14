package ua.phone.book.dao.interfaces;

import ua.phone.book.models.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> getEmployeesByDepartmentId(int departmentId);

    int getEmployeeCountByPositionId(int id);

    void insertEmployee(Employee employee);

    void updateEmployee(Employee employee);

    int getEmployeeCountByDepartmentId(int id);

    void deleteEmployee(int id);

    List<Employee> searchEmployee(String request);
}
