package ru.organizer.orgdata.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.organizer.orgdata.enums.Sex;
import ru.organizer.orgdata.models.ClubAddApplication;
import ru.organizer.orgdata.models.Corporation;
import ru.organizer.orgdata.services.CorporationService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/corporation")
public class CorporationController {

    @Autowired
    private CorporationService service;

    @GetMapping("/new")
    public String newCorporation(Model model) {
        model.addAttribute("corporation", new Corporation());
        return "Corporation/new";
    }

    @GetMapping()
    public String showAll(Model model) {
        List<Corporation> corporation = service.findAll();
        if (!corporation.isEmpty()) {
            model.addAttribute("corporation", corporation.get(0));
        } else {
            model.addAttribute("noCorporation", "Корпорация не создана");
        }
        return "Corporation/showOne";
    }
    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("corporation", service.findAll().get(0));
        return "Corporation/edit";
    }

    @PostMapping()
    public String create(@ModelAttribute("corporation") @Valid Corporation corporation, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "Corporation/new";
        }
        service.save(corporation);
        return "redirect:/corporation";
    }

    @PatchMapping("/edit/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("corporation") @Valid Corporation corporation, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "Corporation/edit";
        }
        service.update(id, corporation);
        return "redirect:/corporation";
    }
}
