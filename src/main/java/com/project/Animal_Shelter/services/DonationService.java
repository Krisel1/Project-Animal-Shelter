package com.project.Animal_Shelter.services;

import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.repositories.IDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {

    @Autowired
    IDonationRepository iDonationRepository;


        public Donation getDonationByID (Long id){
            return iDonationRepository.findById(id).orElseThrow();
        }

        public void updateDonation (Donation donation,Long id){
            donation.setId(id);
            iDonationRepository.save(donation);
        }

        public Donation createDonation (Donation donation){
            return iDonationRepository.save(donation);

        }

    public void deleteDonation(Long id) {
        Donation donation = getDonationByID(id);
        iDonationRepository.delete(donation);
    }

    public List<Donation> getAllDonations() {
        return (List<Donation>) iDonationRepository.findAll();
    }
}