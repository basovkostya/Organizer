package ru.organizer.orgdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.organizer.orgdata.enums.ApplicationStatus;
import ru.organizer.orgdata.models.ClubAddApplication;
import ru.organizer.orgdata.repositories.ClubAddApplicationRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClubAddApplicationService {
    @Autowired
    private ClubAddApplicationRepository clubRepository;

    public List<ClubAddApplication> findAll() {
        return clubRepository.findAll();
    }

    public ClubAddApplication findById(int id) {
        return clubRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(ClubAddApplication clubAddApplication) {
        clubAddApplication.setStatus(ApplicationStatus.НОВЫЙ.getName());
        clubAddApplication.setCreatedDate(new Date());
        clubRepository.save(clubAddApplication);
    }

    @Transactional
    public void update(int id, ClubAddApplication clubAddApplication) {
        clubAddApplication.setId(id);
        clubRepository.save(clubAddApplication);
    }

    @Transactional
    public void delete(int id) {
        clubRepository.deleteById(id);
    }
}
