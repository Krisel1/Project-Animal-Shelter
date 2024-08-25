package com.project.Animal_Shelter.controllers;

import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class PetController {

    @Autowired
    PetService petService;

    @PostMapping

    public Pet createPet(@RequestBody Pet pet) {
        return petService.createPet(pet);
    }
}
