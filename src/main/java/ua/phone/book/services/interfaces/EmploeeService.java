package ua.phone.book.services.interfaces;

import ua.phone.book.models.Department;
import ua.phone.book.models.Emploee;
import ua.phone.book.models.Position;

import java.util.List;

public interface EmploeeService {

    List<Department> getDepartmentsWithoutInfo();

    void addEmploee(Emploee emploee);

    List<Department> getAllDepartments();

    List<Position> getAllPositions();

    void deleteEmploee(int id);

    Department getDepartmentById(int id);

    Position getPositionById(int id);

    void updateEmploee(Emploee emploee);

    void addPosition(Position position);

    void updatePosition(Position position);

    void deletePositiob(int id);

    void addDepartment(Department department);

    void deleteDepartment(int id);

    void updateDepartment(Department department);

    List<Emploee> searchEmploee(String request);
}
