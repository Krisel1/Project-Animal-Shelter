package com.project.Animal_Shelter.repositories;

import com.project.Animal_Shelter.models.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPetRepository extends CrudRepository<Pet, Long> {
}
