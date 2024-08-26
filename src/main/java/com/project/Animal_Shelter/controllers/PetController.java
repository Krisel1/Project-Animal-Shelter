package com.project.Animal_Shelter.controllers;

import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    PetService petService;

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }
    @GetMapping(path = "/{id}")
    public Pet getPetByID(@PathVariable Long id) {
        return petService.getPetByID(id);
    }
    @GetMapping(path = "/withoutAdopted")
    public List<Pet> getAllAnimalsWithoutAdopted() {
        return petService.getAllAnimalsWithoutAdopted();
    }

    @GetMapping(path = "/adopted/{user_id}")
    public List<Pet> getAllByUserId(@PathVariable Long user_id) {
      return petService.getAllByUserId(user_id);
    }
    @GetMapping(path = "/adopted")
    public List<Pet> getAllAdopted() {
        return petService.getAllAnimalsAdopted();
    }

    @DeleteMapping(path = "/{id}")
    public void deletePet(@PathVariable("id") Long id) {
       petService.deletePet(id);
    }

    @PutMapping(path = "/{id}")
    public void updatePet(@RequestBody Pet pet, @PathVariable Long id) {
        petService.updatePet(pet, id);
    }

    @PostMapping(path = "/{id}")
    public Pet createPet(@RequestBody Pet pet) {
        return petService.createPet(pet);
    }

}
