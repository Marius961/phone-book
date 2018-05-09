package ua.phone.book.dao.interfaces;

import ua.phone.book.models.Department;

import java.util.List;

public interface DepartmentDAO {

    List<Department> getAllDepartments();

    Department getDepartmentById(int id);

    int insertDepartment(Department department);

    void updateDepartment(Department department);



    void deleteDepartment(int id);
}
