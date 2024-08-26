package com.project.Animal_Shelter.services;

import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.repositories.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    IPetRepository iPetRepository;

    public void deletePet(long id) {
        iPetRepository.deleteById(id);
    }

    public void updatePet(Pet pet, long id) {
        pet.setId(id);
        iPetRepository.save(pet);
    }
}
