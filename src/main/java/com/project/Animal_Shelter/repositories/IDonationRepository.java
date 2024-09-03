package com.project.Animal_Shelter.repositories;

import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.models.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IDonationRepository extends CrudRepository<Donation, Long> {
    List<Donation> findByUserId(Long userId);
}
