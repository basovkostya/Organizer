package ru.organizer.orgdata.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.organizer.orgdata.models.Organization;
import ru.organizer.orgdata.services.OrganizationService;

@Controller
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    public OrganizationService organizationService;

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("organizations", organizationService.findAll());
        return "Organizations/showAll";
    }

    @GetMapping("/new")
    public String newOrganization(Model model) {
        model.addAttribute("organization", new Organization());
        return "Organizations/new";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("organization", organizationService.findBuId(id));
        return "Organizations/edit";
    }

    @PostMapping()
    public String create(@ModelAttribute("organization") @Valid Organization organization, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "Organizations/new";
        }
        organizationService.save(organization);
        return "redirect:/showAll";
    }
}
