package com.project.Animal_Shelter.controllers;

import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/donations")
public class DonationController {

    @Autowired
    DonationService donationService;

    @GetMapping
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }
    @GetMapping(path = "/{id}")
    public Donation getDonationByID(@PathVariable Long id) {
        return donationService.getDonationByID(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteDonationById(@PathVariable("id") Long id) {
        donationService.deleteDonation(id);
    }
    @PutMapping(path = "/update/{id}")
    public void updateDonation(@RequestBody Donation donation, @PathVariable Long id) {
        donationService.updateDonation(donation, id);
    }
    @PostMapping(path = "/create")
    public Donation createDonation(@RequestBody Donation donation) {
        return donationService.createDonation(donation);


    }
}
