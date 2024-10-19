package ru.organizer.orgdata.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.organizer.orgdata.models.EventAddApplication;
import ru.organizer.orgdata.services.EventAddApplicationService;

@RestController
@RequestMapping("/eventApplications")
public class EventAddApplicationRestController {

    @Autowired
    private EventAddApplicationService service;

    @GetMapping("/{id}")
    public EventAddApplication findById(@PathVariable("id") int id){
       return service.findById(id);
    }
}
