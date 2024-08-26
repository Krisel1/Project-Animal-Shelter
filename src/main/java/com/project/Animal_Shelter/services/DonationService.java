package com.project.Animal_Shelter.services;

import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.repositories.IDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationService {

    @Autowired
    IDonationRepository iDonationRepository;

    public void deleteDonation(long id) {
        iDonationRepository.deleteById(id);
    }
    public void updateDonation(Donation donation, long id) {
        donation.setId(id);
        iDonationRepository.save(donation);
    }

}
