package ua.phone.book.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.phone.book.dao.interfaces.DepartmentDAO;
import ua.phone.book.dao.interfaces.EmployeeDAO;
import ua.phone.book.dao.interfaces.PositionDAO;
import ua.phone.book.models.Department;
import ua.phone.book.models.Employee;
import ua.phone.book.models.Position;
import ua.phone.book.services.interfaces.EmploeeService;

import java.util.List;

@Service
public class EmploeeServiceImpl implements EmploeeService {

    private DepartmentDAO departmentDAO;
    private EmployeeDAO employeeDAO;
    private PositionDAO positionDAO;
    @Autowired
    private void setDAOs(DepartmentDAO departmentDAO, EmployeeDAO employeeDAO, PositionDAO positionDAO) {
        this.departmentDAO = departmentDAO;
        this.employeeDAO = employeeDAO;
        this.positionDAO = positionDAO;
    }

    @Override
    public List<Department> getDepartmentsWithoutInfo() {
        return departmentDAO.getAllDepartments();
    }

    @Override
    public void addEmploee(Employee employee) {
        employeeDAO.insertEmployee(employee);
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = departmentDAO.getAllDepartments();
        for (Department department : departments) {
            setDepartmentDeleteStatus(department);
            department.setEmploeeCount(employeeDAO.getEmployeeCountByDepartmentId(department.getId()));
            department.setEmployeeList(getEmploeesByDepartamentId(department.getId()));
        }
        return departments;
    }

    @Override
    public List<Position> getAllPositions() {
        List<Position> positions = positionDAO.getAllPositions();
        for (Position position : positions) {
            setPositionDeleteStatus(position);
        }
        return positions;
    }

    @Override
    public void deleteEmploee(int id) {
        employeeDAO.deleteEmployee(id);
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
    public void updateEmploee(Employee employee) {
        employeeDAO.updateEmployee(employee);
    }

    @Override
    public void addPosition(Position position) {
        positionDAO.insertPosition(position);
    }

    @Override
    public void updatePosition(Position position) {
        positionDAO.updatePosition(position);
    }

    @Override
    public void deletePositiob(int id) {
        positionDAO.deletePosition(id);
    }

    @Override
    public void addDepartment(Department department) {
        departmentDAO.insertDepartment(department);
    }

    @Override
    public void deleteDepartment(int id) {
        departmentDAO.deleteDepartment(id);
    }

    @Override
    public void updateDepartment(Department department) {
        departmentDAO.updateDepartment(department);
    }

    @Override
    public List<Employee> searchEmploee(String request) {
        List<Employee> employeeList = null;
        if (request != null) {
            employeeList =  employeeDAO.searchEmployee(request);
            for (Employee employee : employeeList) {
                employee.setPosition(positionDAO.getPositionById(employee.getPositionId()));
                employee.setDepartment(departmentDAO.getDepartmentById(employee.getDepartmentId()));
            }
        }
        return employeeList;
    }

    private List<Employee> getEmploeesByDepartamentId(int departmentId) {
       List<Employee> employeeList = employeeDAO.getEmployeesByDepartmentId(departmentId);
       for (Employee employee : employeeList) {
           employee.setPosition(positionDAO.getPositionById(employee.getPositionId()));
       }
       return employeeList;
    }

    private void setPositionDeleteStatus(Position position) {
        int emploeeCount = employeeDAO.getEmployeeCountByPositionId(position.getId());
        if (emploeeCount <= 0) {
            position.setCanDelete(true);
        }
    }

    private void setDepartmentDeleteStatus(Department department) {
        int emploeeCount = employeeDAO.getEmployeeCountByDepartmentId(department.getId());
        if (emploeeCount <= 0) {
            department.setCanDelete(true);
        }
    }
}
