package ru.organizer.orgdata.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.organizer.orgdata.enums.Sex;
import ru.organizer.orgdata.models.Employee;
import ru.organizer.orgdata.models.EventAddApplication;
import ru.organizer.orgdata.services.EmployeeService;
import ru.organizer.orgdata.utils.EmployeeValidator;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeValidator employeeValidator;

    @GetMapping()
    public String findAll(Model model) {
        List<Employee> employeesList = service.findAll();
        if (!employeesList.isEmpty()) {
            model.addAttribute("employees", employeesList);
        } else {
            model.addAttribute("employeesEmpty", "");
        }
        return "Employees/showAll";
    }
    @GetMapping("/view/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", service.findById(id));
        return "Employees/showOne";
    }

    @GetMapping("/new")
    public String newEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("sexTypes", Sex.values());
        return "Employees/new";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("employee", service.findById(id));
        model.addAttribute("sexTypes", Sex.values());
        return "Employees/edit";
    }

    @PostMapping()
    public String create(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult, Model model) {
        employeeValidator.validate(employee, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("sexTypes", Sex.values());
            return "Employees/new";
        }
        service.save(employee);
        return "redirect:/employees";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("employee") @Valid Employee employee
            , BindingResult bindingResult, Model model) {
        employeeValidator.validate(employee, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("sexTypes", Sex.values());
            return "Employees/edit";
        }
        service.update(id, employee);
        return "redirect:/employees";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable("id") int id) {
        service.delete(id);
        return "redirect:/employees";
    }

}
