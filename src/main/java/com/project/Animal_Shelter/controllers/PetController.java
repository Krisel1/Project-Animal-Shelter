package com.project.Animal_Shelter.controllers;

import com.project.Animal_Shelter.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class PetController {

    @Autowired
    PetService petService;

    @DeleteMapping(path = "/{id}")
    public void deletePet(@PathVariable("id") Long id) {
       petService.deletePet(id);
    }



}
