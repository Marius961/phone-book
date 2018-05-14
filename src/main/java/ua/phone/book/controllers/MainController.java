package ua.phone.book.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.phone.book.models.Department;
import ua.phone.book.models.Employee;
import ua.phone.book.models.Position;
import ua.phone.book.models.SearchedObject;
import ua.phone.book.services.interfaces.EmploeeService;
import ua.phone.book.validators.BaseValidator;
import ua.phone.book.validators.EmployeeValidator;

@Controller
public class MainController {

    private EmploeeService emploeeService;
    private BaseValidator baseValidator;
    private EmployeeValidator employeeValidator;

    @Autowired
    private void setEmploeeService(EmploeeService emploeeService) {
        this.emploeeService = emploeeService;
    }

    @Autowired
    private void setValidators(BaseValidator baseValidator, EmployeeValidator employeeValidator) {
        this.baseValidator = baseValidator;
        this.employeeValidator = employeeValidator;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("departments", emploeeService.getAllDepartments());
        modelAndView.addObject("employee", new Employee());
        modelAndView.addObject("searchedObject", new SearchedObject());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/add-employee/{departmentId}", method = RequestMethod.GET)
    public ModelAndView getAddForm(@ModelAttribute Employee employee, @PathVariable int departmentId) {
        if (departmentId == 0) {
            return new ModelAndView("redirect:/");
        } else {
            return getEmploeePage(employee);
        }
    }

    @RequestMapping(value = "/process-employee", method = RequestMethod.POST)
    public ModelAndView processEmploee(@ModelAttribute Employee employee, BindingResult bindingResult) {
        employeeValidator.validate(employee, bindingResult);
        if (!bindingResult.hasErrors()) {
            if (employee.getId() == 0) {
                emploeeService.addEmploee(employee);
            } else {
                emploeeService.updateEmploee(employee);
            }
            return new ModelAndView("redirect:/");
        } else {
            return getEmploeePage(employee);
        }
    }

    private ModelAndView getEmploeePage(Employee employee) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("positions", emploeeService.getAllPositions());
        modelAndView.addObject("departments", emploeeService.getDepartmentsWithoutInfo());
        modelAndView.setViewName("add-employee");
        return modelAndView;
    }

    @RequestMapping(value = "/delete-employee/{id}", method = RequestMethod.GET)
    public String deleteEmploee(@PathVariable int id) {
        emploeeService.deleteEmploee(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit-employee", method = RequestMethod.POST)
    public ModelAndView getEditForm(@ModelAttribute Employee employee) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("emplyoee", employee);
        modelAndView.addObject("positions", emploeeService.getAllPositions());
        modelAndView.addObject("departments", emploeeService.getDepartmentsWithoutInfo());
        modelAndView.addObject("header", "Редагування працівника");
        modelAndView.setViewName("add-employee");
        return modelAndView;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView getInfoPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("positions", emploeeService.getAllPositions());
        modelAndView.addObject("departments", emploeeService.getAllDepartments());
        modelAndView.setViewName("full-info");
        return modelAndView;
    }


    @RequestMapping(value = "/add-position", method = RequestMethod.GET)
    public ModelAndView getAddPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("position", new Position());
        modelAndView.setViewName("add-position");
        return modelAndView;
    }

    @RequestMapping(value = "/process-position", method = RequestMethod.POST)
    public ModelAndView processValue(@ModelAttribute Position position, BindingResult bindingResult) {
        baseValidator.validate(position, bindingResult);
        if (!bindingResult.hasErrors()) {
            if (position.getId() == 0) {
                emploeeService.addPosition(position);
            } else {
                emploeeService.updatePosition(position);
            }
            return new ModelAndView("redirect:/info");
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("position", position);
            modelAndView.setViewName("add-position");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/edit-position/{id}", method = RequestMethod.GET)
    public ModelAndView getEditPage(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("position", emploeeService.getPositionById(id));
        modelAndView.setViewName("add-position");
        return modelAndView;
    }

    @RequestMapping(value = "/delete-position/{id}", method = RequestMethod.GET)
    public String deletePosition(@PathVariable int id) {
        emploeeService.deletePositiob(id);
        return "redirect:/info";
    }

    @RequestMapping(value = "/add-department", method = RequestMethod.GET)
    public ModelAndView getAddDepartmentPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("department", new Department());
        modelAndView.setViewName("add-department");
        return modelAndView;
    }

    @RequestMapping(value = "/process-department", method = RequestMethod.POST)
    public ModelAndView processDepartment(@ModelAttribute Department department, BindingResult bindingResult) {
        baseValidator.validate(department, bindingResult);
        if (!bindingResult.hasErrors()) {
            if (department.getId() == 0) {
                emploeeService.addDepartment(department);
            } else {
                emploeeService.updateDepartment(department);
            }
            return new ModelAndView("redirect:/info");
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("department", department);
            modelAndView.setViewName("add-department");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/edit-department/{id}", method = RequestMethod.GET)
    public ModelAndView getEditDepartmentPage(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("department", emploeeService.getDepartmentById(id));
        modelAndView.setViewName("add-department");
        return modelAndView;
    }

    @RequestMapping(value = "/delete-department/{id}", method = RequestMethod.GET)
    public String deleteDepartment(@PathVariable int id) {
        emploeeService.deleteDepartment(id);
        return "redirect:/info";
    }

    @RequestMapping(value = "/search-employee", method = RequestMethod.POST)
    public ModelAndView getSearchResults(@ModelAttribute SearchedObject searchedObject) {
        if (!searchedObject.getObjectName().equals("")) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("employee", new Employee());
            modelAndView.addObject("searchedObject", searchedObject);
            modelAndView.addObject("results", emploeeService.searchEmploee(searchedObject.getObjectName()));
            modelAndView.setViewName("search-results");
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/");
        }
    }




}
