package ru.organizer.orgdata.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.organizer.orgdata.models.Employee;
import ru.organizer.orgdata.models.EmployeeAddApplication;
import ru.organizer.orgdata.services.EmployeeAddApplicationService;
import ru.organizer.orgdata.services.EmployeeService;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmployeeValidator implements Validator {
    @Autowired
    private EmployeeService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee =(Employee) target;
        Optional<Employee> employeeWithPassport = service.findByPassportNumber(employee.getPassportNumber());
        Optional<Employee> employeeWithEmail = service.findByEmail(employee.getEmail().toLowerCase());
        Optional<Employee> employeeWithSnils = service.findBySnils(employee.getSnils());
        if (employeeWithEmail.isPresent() && (!employeeWithEmail.get().getId().equals(employee.getId()))) {
            errors.rejectValue("email", "", "Сотрудник с таким почтовым адресом существует");
        }
        if (employeeWithSnils.isPresent() && (!employeeWithSnils.get().getId().equals(employee.getId()))) {
            errors.rejectValue("snils", "", "Сотрудник с таким номером снилс существует");
        }
        if (employeeWithPassport.isPresent() && (!employeeWithPassport.get().getId().equals(employee.getId()))) {
            errors.rejectValue("passportNumber", "", "Сотрудник с таким номером паспорта существует");
        }

        String snils = String.valueOf(employee.getSnils());
        boolean matchesSnils = snils.matches("^\\d{11}");
        if(!matchesSnils){
            errors.rejectValue("snils", "", "СНИЛС должен содержать 11 цифр");
        }
        String passport = String.valueOf(employee.getPassportNumber());
        boolean matchesPassport = passport.matches("^\\d{10}");
        if(!matchesPassport){
            errors.rejectValue("passportNumber", "", "серия и номер должны содержать 10 цифр");
        }
    }
}
