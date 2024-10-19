package ru.organizer.orgdata.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.organizer.orgdata.enums.Sex;
import ru.organizer.orgdata.models.ClubAddApplication;
import ru.organizer.orgdata.models.Employee;
import ru.organizer.orgdata.models.EmployeeAddApplication;
import ru.organizer.orgdata.services.ClubAddApplicationService;
import ru.organizer.orgdata.services.EmployeeAddApplicationService;
import ru.organizer.orgdata.services.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employeeAddApplications")
public class EmployeeAddApplicationController {
    @Autowired
    private EmployeeAddApplicationService service;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String findAll(Model model) {
        List<EmployeeAddApplication> employeeAddApplicationList = service.findAll();
        if (!employeeAddApplicationList.isEmpty()) {
            model.addAttribute("addApplications", employeeAddApplicationList);
        } else {
            model.addAttribute("addApplicationsEmpty", "");
        }
        return "EmployeeAddApplications/showAll";
    }

    @GetMapping("/addEmployees/{id}")
    public String findEmployees(@PathVariable("id") int id, Model model) {
        List<Employee> employeesList = employeeService.findAll();
        if (!employeesList.isEmpty()) {
            model.addAttribute("employeeAddApplication", service.findById(id));
            model.addAttribute("employees", employeesList);
        } else {
            model.addAttribute("employeesEmpty", "");
        }
        return "EmployeeAddApplications/addEmployees";
    }

    @GetMapping("/view/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        EmployeeAddApplication findEmployeeAddApplication = service.findById(id);
        model.addAttribute("employeeAddApplication", findEmployeeAddApplication);
        model.addAttribute("employees", findEmployeeAddApplication.getEmployee());
        return "EmployeeAddApplications/showOne";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("addApplication", new EmployeeAddApplication());
        return "EmployeeAddApplications/new";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("addApplication", service.findById(id));
        return "EmployeeAddApplications/edit";
    }

    @PostMapping()
    public String create(@ModelAttribute("addApplication") @Valid EmployeeAddApplication employeeAddApplication, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "EmployeeAddApplications/new";
        }
        service.save(employeeAddApplication);
        return "redirect:/employeeAddApplications";
    }

    @PostMapping("/addEmployees/add/{id}")
    public String addEmployeeToList(@RequestParam("empId") int empId, @PathVariable("id") int id,
                                   @ModelAttribute("employeeAddApplication")
                                    EmployeeAddApplication employeeAddApplication, Model model) {
        Employee findEmployee = employeeService.findById(empId);
        EmployeeAddApplication findEmployeeAddApplication = service.findById(id);
        service.addEmployeeToList(findEmployee, findEmployeeAddApplication);
        model.addAttribute("employeeAddApplication", service.findById(id));
        model.addAttribute("employees", employeeService.findAll());
        return "EmployeeAddApplications/addEmployees";
    }

    @PostMapping("/addEmployees/remove/{id}")
    public String removeEmployeeFromList(@RequestParam("empId") int empId, @PathVariable("id") int id,
                                   @ModelAttribute("employeeAddApplication")
                                           EmployeeAddApplication employeeAddApplication, Model model) {
        Employee findEmployee = employeeService.findById(empId);
        EmployeeAddApplication findEmployeeAddApplication = service.findById(id);
        service.removeEmployeeFromList(findEmployee, findEmployeeAddApplication);
        model.addAttribute("employeeAddApplication", service.findById(id));
        model.addAttribute("employees", employeeService.findAll());
        return "EmployeeAddApplications/addEmployees";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("addApplication") @Valid EmployeeAddApplication employeeAddApplication
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "EmployeeAddApplications/edit";
        }
        service.update(id, employeeAddApplication);
        return "redirect:/employeeAddApplications";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable("id") int id) {
        service.delete(id);
        return "redirect:/employeeAddApplications";
    }
}
