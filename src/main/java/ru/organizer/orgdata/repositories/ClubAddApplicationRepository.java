package ru.organizer.orgdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.organizer.orgdata.models.ClubAddApplication;
@Repository
public interface ClubAddApplicationRepository extends JpaRepository<ClubAddApplication, Integer> {
    ClubAddApplication findByName(String name);
}
