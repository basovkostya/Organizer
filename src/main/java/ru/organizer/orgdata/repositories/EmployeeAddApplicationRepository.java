package ru.organizer.orgdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.organizer.orgdata.models.Employee;
import ru.organizer.orgdata.models.EmployeeAddApplication;

import java.util.Optional;

@Repository
public interface EmployeeAddApplicationRepository extends JpaRepository<EmployeeAddApplication, Integer> {

}
