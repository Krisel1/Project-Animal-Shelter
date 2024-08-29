package com.project.Animal_Shelter.controllers;

import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donations")
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

    @DeleteMapping(path = "/{id}")
    public void deleteDonationById(@PathVariable("id") Long id) {
        donationService.deleteDonation(id);
    }
    @PutMapping(path = "/{id}")
    public void updateDonation(@RequestBody Donation donation, @PathVariable Long id) {
        donationService.updateDonation(donation, id);
    }
    @PostMapping
    public ResponseEntity<Donation> createDonation(@RequestBody Donation donation) {
        Donation createdDonation = donationService.createDonation(donation);
        return ResponseEntity.ok(createdDonation);

    }
}