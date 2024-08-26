package com.project.Animal_Shelter.controllers;

import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class PetController {

    @Autowired
    PetService petService;

    @DeleteMapping(path = "/{id}")
    public void deletePet(@PathVariable("id") Long id) {
       petService.deletePet(id);
    }

    @PutMapping(path = "/{id}")
    public void updatePet(@RequestBody Pet pet, @PathVariable Long id) {
        petService.updatePet(pet, id);
    }



}
