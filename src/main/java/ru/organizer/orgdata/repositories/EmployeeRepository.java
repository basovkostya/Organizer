package ru.organizer.orgdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.organizer.orgdata.models.Employee;
import ru.organizer.orgdata.models.EmployeeAddApplication;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByEmail(String email);
    List<Employee> findBySnils(long snils);
    List<Employee> findByPassportNumber(long passportNumber);
}
