package com.project.Animal_Shelter.repositories;

import com.project.Animal_Shelter.models.Announcement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnnouncementRepository extends CrudRepository<Announcement, Long> {
}
