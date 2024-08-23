package com.project.Animal_Shelter.repositories;

import com.project.Animal_Shelter.models.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPetRepository extends CrudRepository<Pet, Long> {
    List<Pet> findByUserId(Long userId);
}
