package com.project.Animal_Shelter.services;

import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.models.User;
import com.project.Animal_Shelter.repositories.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {

    @Autowired
    IPetRepository iPetRepository;

    public List<Pet> getAllPets() {
        return (List<Pet>) iPetRepository.findAll();
    }
    public Pet getPetByID(Long id) {
        return iPetRepository.findById(id).orElseThrow();
    }
    public List<Pet> getAllAnimalsWithoutAdopted() {
        ArrayList<Pet> allNotAdoptedAnimalsList = new ArrayList<>();
        List<Pet> allPets = (List<Pet>) iPetRepository.findAll();
        for (Pet pet : allPets) {
            if(!pet.isAdopted()) {
                allNotAdoptedAnimalsList.add(pet);
            }
        }
        return allNotAdoptedAnimalsList;
    }

    public List<Pet> getAllByUserId(Long user_id) {
        return iPetRepository.findByUserId(user_id);
    }
    public List<Pet> getAllAnimalsAdopted() {
        ArrayList<Pet> adoptedAnimalsList = new ArrayList<>();
        List<Pet> allPets = (List<Pet>) iPetRepository.findAll();
        for (Pet pet : allPets) {
            if(pet.isAdopted()) {
                adoptedAnimalsList.add(pet);
            }
        }
        return adoptedAnimalsList;
    }
    public void deletePet(long id) {
        iPetRepository.deleteById(id);
    }

    public void updatePet(Pet pet, long id) {
        pet.setId(id);
        iPetRepository.save(pet);
    }
    public Pet createPet(Pet pet) {
        return iPetRepository.save(pet);
    }
    public Pet adopt(Long pet_id, Long user_id) {
        Pet pet = iPetRepository.findById(pet_id).orElseThrow();
        User user = new User(user_id, null, null,null, null, null, null);
        User secondUser = new User(1L, null, null,null, null, null, null);
        if(pet.isAdopted()) {
            pet.setAdopted(false);
            pet.setUser(secondUser);
        } else {
            pet.setAdopted(true);
            pet.setUser(user);
        }
        iPetRepository.save(pet);
        return pet;
    }
}
