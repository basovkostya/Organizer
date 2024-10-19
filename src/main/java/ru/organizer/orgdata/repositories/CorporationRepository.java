package ru.organizer.orgdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.organizer.orgdata.models.Corporation;

import java.util.List;

@Repository
public interface CorporationRepository extends JpaRepository<Corporation, Integer> {
}
