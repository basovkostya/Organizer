package ru.organizer.orgdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.organizer.orgdata.enums.ApplicationStatus;
import ru.organizer.orgdata.models.Employee;
import ru.organizer.orgdata.models.EmployeeAddApplication;
import ru.organizer.orgdata.repositories.EmployeeAddApplicationRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class EmployeeAddApplicationService {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeAddApplicationRepository addRepository;

    public List<EmployeeAddApplication> findAll() {
        return addRepository.findAll();
    }

    public EmployeeAddApplication findById(int id) {
        return addRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(EmployeeAddApplication employeeAddApplication) {
        employeeAddApplication.setStatus(ApplicationStatus.НОВЫЙ.getName());
        employeeAddApplication.setCreatedDate(new Date());
        addRepository.save(employeeAddApplication);
    }

    @Transactional
    public void update(int id, EmployeeAddApplication employeeAddApplication) {
        employeeAddApplication.setId(id);
        addRepository.save(employeeAddApplication);
    }

    @Transactional
    public void delete(int id) {
        addRepository.deleteById(id);
    }

    @Transactional
    public void selectEmployee(int id, Employee employee) {
        Optional<EmployeeAddApplication> addApplication = addRepository.findById(id);
    }

    public List<Employee> getEmployeesByApplicationId(int id) {
        return addRepository.findById(id).orElseThrow().getEmployee();
    }

    @Transactional
    public void addEmployeeToList(Employee employee, EmployeeAddApplication employeeAddApplication) {
       employee.setEmployeeAddApplication(employeeAddApplication);
        if (employeeAddApplication.getEmployee() == null) {
            List<Employee> employeeList = new ArrayList<>();
            employeeList.add(employee);
            employeeAddApplication.setEmployee(employeeList);
        }
    }

    @Transactional
    public void removeEmployeeFromList(Employee employee, EmployeeAddApplication employeeAddApplication) {
        employee.setEmployeeAddApplication(null);
        employeeAddApplication.getEmployee().remove(employee);
    }
}
