package ru.organizer.orgdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.organizer.orgdata.models.EmployeeDeleteApplication;
import ru.organizer.orgdata.repositories.EmployeeDeleteApplicationRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeDeleteApplicationService {
    @Autowired
    private EmployeeDeleteApplicationRepository deleteRepository;

    public List<EmployeeDeleteApplication> findAll() {
        return deleteRepository.findAll();
    }

    public EmployeeDeleteApplication findById(int id) {
        return deleteRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(EmployeeDeleteApplication userDeleteApplication) {
        deleteRepository.save(userDeleteApplication);
    }

    @Transactional
    public void update(int id, EmployeeDeleteApplication employeeDeleteApplication) {
        employeeDeleteApplication.setId(id);
        deleteRepository.save(employeeDeleteApplication);
    }

    @Transactional
    public void delete(int id) {
        deleteRepository.deleteById(id);
    }
}
