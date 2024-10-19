package ru.organizer.orgdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.organizer.orgdata.models.EmployeeDeleteApplication;

@Repository
public interface EmployeeDeleteApplicationRepository extends JpaRepository<EmployeeDeleteApplication, Integer> {
}
