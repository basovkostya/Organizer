package ru.organizer.orgdata.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.organizer.orgdata.enums.Currency;
import ru.organizer.orgdata.enums.EventType;
import ru.organizer.orgdata.enums.SportType;
import ru.organizer.orgdata.enums.TournamentType;
import ru.organizer.orgdata.models.ClubAddApplication;
import ru.organizer.orgdata.models.EmployeeDeleteApplication;
import ru.organizer.orgdata.models.EventAddApplication;
import ru.organizer.orgdata.services.ClubAddApplicationService;
import ru.organizer.orgdata.services.EventAddApplicationService;
import ru.organizer.orgdata.utils.EventValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/eventApplications")
public class EventAddApplicationController {
    @Autowired
    private EventAddApplicationService service;
    @Autowired
    private EventValidator eventValidator;

    @GetMapping
    public String findAll(Model model) {
        List<EventAddApplication> eventAddApplicationList = service.findAll();
        if (!eventAddApplicationList.isEmpty()) {
            model.addAttribute("eventApplications", eventAddApplicationList);
        } else {
            model.addAttribute("eventApplicationsEmpty", "");
        }
        return "EventApplications/showAll";
    }

    @GetMapping("/view/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("eventApplication", service.findById(id));
        return "EventApplications/showOne";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("eventApplication", service.findById(id));
        model.addAttribute("currencies", Currency.values());
        model.addAttribute("eventTypes", EventType.values());
        model.addAttribute("sportTypes", SportType.values());
        model.addAttribute("tournamentTypes", TournamentType.values());
        return "EventApplications/edit";
    }

    @GetMapping("/new")
    public String newEvent(Model model) {
        model.addAttribute("eventApplication", new EventAddApplication());
        model.addAttribute("currencies", Currency.values());
        model.addAttribute("eventTypes", EventType.values());
        model.addAttribute("sportTypes", SportType.values());
        model.addAttribute("tournamentTypes", TournamentType.values());
        return "EventApplications/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("eventApplication") @Valid EventAddApplication eventAddApplication, BindingResult bindingResult, Model model) {
        eventValidator.validate(eventAddApplication, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("currencies", Currency.values());
            model.addAttribute("eventTypes", EventType.values());
            model.addAttribute("sportTypes", SportType.values());
            model.addAttribute("tournamentTypes", TournamentType.values());
            return "EventApplications/new";
        }
        service.save(eventAddApplication);
        return "redirect:/eventApplications";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("eventApplication") @Valid EventAddApplication eventAddApplication
            , BindingResult bindingResult, Model model) {
        eventValidator.validate(eventAddApplication, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("currencies", Currency.values());
            model.addAttribute("eventTypes", EventType.values());
            model.addAttribute("sportTypes", SportType.values());
            model.addAttribute("tournamentTypes", TournamentType.values());
            return "EventApplications/edit";
        }
        service.update(id, eventAddApplication);
        return "redirect:/eventApplications";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable("id") int id) {
        service.delete(id);
        return "redirect:/eventApplications";
    }
}
