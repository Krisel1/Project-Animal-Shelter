package com.project.Animal_Shelter.repositories;

import com.project.Animal_Shelter.models.Donation;
import org.springframework.data.repository.CrudRepository;

public interface IDonationRepository extends CrudRepository<Donation, Long> {
}
