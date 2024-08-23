package com.project.Animal_Shelter.controllers;

import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class DonationController {

    @Autowired
    DonationService donationService;

    @GetMapping(path = "/donations")
    public List<Donation> getAllDonations() {
        return donationService.getAllDonations();
    }
    @GetMapping(path = "/donations/{id}")
    public Donation getDonationByID(@PathVariable Long id) {
        return donationService.getDonationByID(id);
    }
}
