package ru.organizer.orgdata.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.organizer.orgdata.models.Organization;
import ru.organizer.orgdata.services.OrganizationService;

import java.util.Optional;

@Component
public class OrganizationValidator implements Validator {

    @Autowired
    private OrganizationService organizationService;
    @Override
    public boolean supports(Class<?> clazz) {
        return Organization.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Organization organization = (Organization) target;
        Optional<Organization> findOrganization = organizationService.findByName(organization.getName());
        if(findOrganization.isPresent() && (!findOrganization.get().getId().equals(organization.getId()))){
            errors.rejectValue("name", "", "Oрганизация с таким названим существует");
        }
    }
}
