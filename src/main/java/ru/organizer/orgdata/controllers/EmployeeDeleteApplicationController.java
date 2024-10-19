package ru.organizer.orgdata.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.organizer.orgdata.models.Employee;
import ru.organizer.orgdata.models.EmployeeAddApplication;
import ru.organizer.orgdata.models.EmployeeDeleteApplication;
import ru.organizer.orgdata.services.EmployeeAddApplicationService;
import ru.organizer.orgdata.services.EmployeeDeleteApplicationService;
import ru.organizer.orgdata.services.EmployeeService;

import java.util.List;
@Controller
@RequestMapping("/employeeDeleteApplications")
public class EmployeeDeleteApplicationController {
    @Autowired
    private EmployeeDeleteApplicationService service;
     @Autowired
     private EmployeeService employeeService;


    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        EmployeeDeleteApplication employeeDeleteApplication = service.findById(id);
        model.addAttribute("employeeDeleteApplication", employeeDeleteApplication);
        List<Employee> employeeList = employeeDeleteApplication.getEmployee();
        if (!employeeList.isEmpty()) {
            model.addAttribute("employees", employeeList);
        } else {
            model.addAttribute("employeesEmpty", "");
        }
        return "EmployeeDeleteApplications/showOne";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("employeeDeleteApplication", service.findById(id));
        return "EmployeeDeleteApplications/edit";
    }
    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("employee", new EmployeeDeleteApplication());
        return "EmployeeDeleteApplications/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("employeeDeleteApplication") @Valid EmployeeDeleteApplication employeeDeleteApplication, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "EmployeeDeleteApplications/new"; //TODO
        }
        service.save(employeeDeleteApplication);
        return "redirect:/employeeAddApplications";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("employeeAddApplication") @Valid EmployeeDeleteApplication employeeDeleteApplication
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "EmployeeDeleteApplications/edit"; //TODO
        }
        service.update(id, employeeDeleteApplication);
        return "redirect:/employeeAddApplications";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        service.delete(id);
        return "redirect:/employeeAddApplications";
    }
}
