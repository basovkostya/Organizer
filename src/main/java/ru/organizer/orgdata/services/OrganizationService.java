package ru.organizer.orgdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.organizer.orgdata.models.Organization;
import ru.organizer.orgdata.repositories.OrganizationRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrganizationService {

    @Autowired
    private OrganizationRepository repository;

    public List<Organization> findAll() {
        return repository.findAll();
    }

    public Organization findBuId(int id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Organization organization) {
        repository.save(organization);
    }

    @Transactional
    public void update(int id, Organization organization){
        organization.setId(id);
        repository.save(organization);
    }

    @Transactional
    public void delete(int id){
        repository.deleteById(id);
    }

    public Optional<Organization> findByName(String name){
       return repository.findByName(name).stream().findAny();
    }
}
