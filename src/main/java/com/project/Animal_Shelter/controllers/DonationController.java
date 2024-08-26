package com.project.Animal_Shelter.controllers;

import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class DonationController {

    @Autowired
    DonationService donationService;

    @DeleteMapping(path = "/{id}")
    public void deleteDonationById(@PathVariable("id") Long id) {
        donationService.deleteDonation(id);
    }
    @PutMapping(path = "/{id}")
    public void updateDonation(@RequestBody Donation donation, @PathVariable Long id) {
        donationService.updateDonation(donation, id);
    }
}
