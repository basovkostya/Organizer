package ru.organizer.orgdata.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.organizer.orgdata.enums.Sex;
import ru.organizer.orgdata.models.ClubAddApplication;
import ru.organizer.orgdata.models.Employee;
import ru.organizer.orgdata.services.ClubAddApplicationService;

import java.util.List;

@Controller
@RequestMapping("/clubApplications")
public class ClubApplicationController {
    @Autowired
    private ClubAddApplicationService service;

    @GetMapping
    public String findAll(Model model) {
        List<ClubAddApplication> clubAddApplicationList = service.findAll();
        if (!clubAddApplicationList.isEmpty()) {
            model.addAttribute("clubApplications", clubAddApplicationList);
        } else {
            model.addAttribute("clubApplicationsEmpty", "");
        }
        return "ClubApplications/showAll";
    }

    @GetMapping("/view/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("clubApplication", service.findById(id));
        return "ClubApplications/showOne";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("clubApplication", service.findById(id));
        model.addAttribute("sexTypes", Sex.values());
        return "ClubApplications/edit";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("clubApplication", new ClubAddApplication());
        model.addAttribute("sexTypes", Sex.values());
        return "ClubApplications/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("clubApplication") @Valid ClubAddApplication clubAddApplication, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("sexTypes", Sex.values());
            return "ClubApplications/new";
        }
        service.save(clubAddApplication);
        return "redirect:/clubApplications";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("clubApplication") @Valid ClubAddApplication clubAddApplication
            , BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("sexTypes", Sex.values());
            return "ClubApplications/edit";
        }
        service.update(id, clubAddApplication);
        return "redirect:/clubApplications";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable("id") int id) {
        service.delete(id);
        return "redirect:/clubApplications";
    }
}

