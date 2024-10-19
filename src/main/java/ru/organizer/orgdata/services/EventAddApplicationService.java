package ru.organizer.orgdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.organizer.orgdata.enums.ApplicationStatus;
import ru.organizer.orgdata.models.EventAddApplication;
import ru.organizer.orgdata.repositories.EventApplicationRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EventAddApplicationService {
    @Autowired
    private EventApplicationRepository eventRepository;

    public List<EventAddApplication> findAll() {
        return eventRepository.findAll();
    }

    public EventAddApplication findById(int id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(EventAddApplication eventAddApplication) {
        eventAddApplication.setStatus(ApplicationStatus.НОВЫЙ.getName());
        eventAddApplication.setCreatedDate(new Date());
        eventRepository.save(eventAddApplication);
    }

    @Transactional
    public void update(int id, EventAddApplication eventAddApplication) {
        eventAddApplication.setId(id);
        eventRepository.save(eventAddApplication);
    }

    @Transactional
    public void delete(int id) {
        eventRepository.deleteById(id);
    }

}
