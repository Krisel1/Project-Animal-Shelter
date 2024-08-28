package com.project.Animal_Shelter.controllers;

import com.project.Animal_Shelter.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DonationController {

    @Autowired
    DonationService donationService;

    @DeleteMapping(path = "/{id}")
    public void deleteDonationById(@PathVariable("id") Long id) {
        donationService.deleteDonation(id);
    }
}