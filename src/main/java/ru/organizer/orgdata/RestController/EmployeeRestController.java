package ru.organizer.orgdata.RestController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.organizer.orgdata.models.Employee;
import ru.organizer.orgdata.services.EmployeeService;
import ru.organizer.orgdata.utils.EmployeeValidator;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") int id) {
        Employee employee = service.findById(id);
        return employee;
    }
}
