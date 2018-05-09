package ua.phone.book.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.phone.book.dao.interfaces.DepartmentDAO;
import ua.phone.book.dao.interfaces.EmploeeDAO;
import ua.phone.book.dao.interfaces.PositionDAO;
import ua.phone.book.models.Department;
import ua.phone.book.models.Emploee;
import ua.phone.book.models.Position;
import ua.phone.book.services.interfaces.EmploeeService;

import java.util.List;

@Service
public class EmploeeServiceImpl implements EmploeeService {

    private DepartmentDAO departmentDAO;
    private EmploeeDAO emploeeDAO;
    private PositionDAO positionDAO;
    @Autowired
    private void setDAOs(DepartmentDAO departmentDAO, EmploeeDAO emploeeDAO, PositionDAO positionDAO) {
        this.departmentDAO = departmentDAO;
        this.emploeeDAO = emploeeDAO;
        this.positionDAO = positionDAO;
    }

    @Override
    public List<Department> getDepartmentsWithoutInfo() {
        return departmentDAO.getAllDepartments();
    }

    @Override
    public void addEmploee(Emploee emploee) {
        emploeeDAO.insertEmploee(emploee);
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = departmentDAO.getAllDepartments();
        for (Department department : departments) {
            department.setEmploeeCount(emploeeDAO.getEmploeeCoutByDepartmentId(department.getId()));
            department.setEmploeeList(getEmploeesByDepartamentId(department.getId()));
        }
        return departments;
    }

    @Override
    public List<Position> getAllPositions() {
        return positionDAO.getAllPositions();
    }

    @Override
    public void deleteEmploee(int id) {
        emploeeDAO.deleteEmploee(id);
    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentDAO.getDepartmentById(id);
    }

    @Override
    public Position getPositionById(int id) {
        return positionDAO.getPositionById(id);
    }

    @Override
    public void updateEmploee(Emploee emploee) {
        emploeeDAO.updateEmploee(emploee);
    }

    private List<Emploee> getEmploeesByDepartamentId(int departmentId) {
       List<Emploee>  emploeeList = emploeeDAO.getEmploeesByDepartamentId(departmentId);
       for (Emploee emploee : emploeeList) {
           emploee.setPosition(positionDAO.getPositionById(emploee.getPositionId()));
       }
       return emploeeList;
    }
}
