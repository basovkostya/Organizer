package ru.organizer.orgdata.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.organizer.orgdata.models.EmployeeAddApplication;
import ru.organizer.orgdata.services.EmployeeAddApplicationService;

@RestController
@RequestMapping("/employeeAddApplications")
public class EmployeeAddApplicationRestController {

    @Autowired
    private EmployeeAddApplicationService service;

    @GetMapping("/{id}")
    public EmployeeAddApplication findById(@PathVariable("id") int id){
        return service.findById(id);
    }
}
