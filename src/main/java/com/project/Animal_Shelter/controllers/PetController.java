package com.project.Animal_Shelter.controllers;

import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping
public class PetController {

    @Autowired
    PetService petService;
    @GetMapping(path = "/pets")
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }
    @GetMapping(path = "/pets/{id}")
    public Pet getPetByID(@PathVariable Long id) {
        return petService.getPetByID(id);
    }
    @GetMapping(path = "/donations/withoutAdopted")
    public List<Pet> getAllAnimalsWithoutAdopted() {
        return (List<Pet>) getAllAnimalsWithoutAdopted();
    }
    @GetMapping(path = "/donations/{user_id}")
    public List<Pet> getAllByUserId(@PathVariable Long user_id) {
      return petService.getAllByUserId(user_id);
    }
}
