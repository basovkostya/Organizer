package ru.organizer.orgdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.organizer.orgdata.models.Employee;
import ru.organizer.orgdata.models.EmployeeAddApplication;
import ru.organizer.orgdata.repositories.EmployeeRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
    public List<Employee> findAllFree() {
        return employeeRepository.findAll().stream().filter(employee -> employee.getEmployeeAddApplication() == null).collect(Collectors.toList());
    }

    public Employee findById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Employee employee) {
        employee.setEmail(employee.getEmail().toLowerCase());
        employee.setAge(calculateAge(employee.getBirthday()));
        employeeRepository.save(employee);
    }

    @Transactional
    public void update(int id, Employee employee) {
        employee.setId(id);
        employee.setAge(calculateAge(employee.getBirthday()));
        employeeRepository.save(employee);
    }

    @Transactional
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

    public Optional<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email).stream().findAny();
    }

    public Optional<Employee> findBySnils(long snils) {
        return employeeRepository.findBySnils(snils).stream().findAny();
    }
    public Optional<Employee> findByPassportNumber(long passportNumber) {
        return employeeRepository.findByPassportNumber(passportNumber).stream().findAny();
    }
    public int calculateAge(Date birthDate) {
        Date currentDate = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birthDate));
        int d2 = Integer.parseInt(formatter.format(currentDate));
        int age = (d2 - d1) / 10000;
        return age;
    }
}
