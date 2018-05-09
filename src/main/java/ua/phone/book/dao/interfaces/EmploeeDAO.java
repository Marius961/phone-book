package ua.phone.book.dao.interfaces;

import ua.phone.book.models.Emploee;

import java.util.List;

public interface EmploeeDAO {
    
    List<Emploee> getEmploeesByDepartamentId(int departmentId);
    
    Emploee getEmploeeById(int id);

    void insertEmploee(Emploee emploee);

    void updateEmploee(Emploee emploee);

    int getEmploeeCoutByDepartmentId(int id);

    void deleteEmploee(int id);
}
