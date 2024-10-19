package ru.organizer.orgdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.organizer.orgdata.models.EventAddApplication;
@Repository
public interface EventApplicationRepository extends JpaRepository<EventAddApplication, Integer> {
}
