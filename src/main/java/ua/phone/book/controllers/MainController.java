package ua.phone.book.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.phone.book.models.Emploee;
import ua.phone.book.services.interfaces.EmploeeService;

@Controller
public class MainController {

    private EmploeeService emploeeService;

    @Autowired
    private void setEmploeeService(EmploeeService emploeeService) {
        this.emploeeService = emploeeService;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("departments", emploeeService.getAllDepartments());
        modelAndView.addObject("emploee", new Emploee());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/add-emploee/{departmentId}", method = RequestMethod.GET)
    public ModelAndView getAddForm(@ModelAttribute Emploee emploee, @PathVariable int departmentId) {
        if (departmentId == 0) {
            return new ModelAndView("redirect:/");
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("emploee", emploee);
            modelAndView.addObject("positions", emploeeService.getAllPositions());
            modelAndView.addObject("departments", emploeeService.getDepartmentsWithoutInfo());
            modelAndView.addObject("header", "Додавання працівника");
            modelAndView.setViewName("add-emploee");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/process-emploee", method = RequestMethod.POST)
    public String processEmploee(@ModelAttribute Emploee emploee) {
        if (emploee.getId() == 0) {
            emploeeService.addEmploee(emploee);
        } else {
            emploeeService.updateEmploee(emploee);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/delete-emploee/{id}", method = RequestMethod.GET)
    public String deleteEmploee(@PathVariable int id) {
        emploeeService.deleteEmploee(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit-emploee", method = RequestMethod.POST)
    public ModelAndView getEditForm(@ModelAttribute Emploee emploee) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("emploee", emploee);
        modelAndView.addObject("positions", emploeeService.getAllPositions());
        modelAndView.addObject("departments", emploeeService.getDepartmentsWithoutInfo());
        modelAndView.addObject("header", "Редагування працівника");
        modelAndView.setViewName("add-emploee");
        return modelAndView;
    }
}
