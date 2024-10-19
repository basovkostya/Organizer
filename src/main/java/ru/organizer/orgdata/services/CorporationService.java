package ru.organizer.orgdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.organizer.orgdata.enums.ApplicationStatus;
import ru.organizer.orgdata.models.Corporation;
import ru.organizer.orgdata.repositories.CorporationRepository;

import java.util.List;

@Service
public class CorporationService {
    @Autowired
    private CorporationRepository corporationRepository;

    public Corporation findById(int id){
        return corporationRepository.findById(id).orElse(null);
    }
    public List<Corporation> findAll(){
        return corporationRepository.findAll();
    }

    @Transactional
    public void save(Corporation corporation) {
        corporation.setStatus(ApplicationStatus.НОВЫЙ.getName());
        corporationRepository.save(corporation);
    }
    @Transactional
    public void update(int id, Corporation corporation) {
        corporation.setId(id);
        corporationRepository.save(corporation);
    }
}
