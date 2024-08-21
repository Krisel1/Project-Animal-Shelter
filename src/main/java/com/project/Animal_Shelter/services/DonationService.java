package com.project.Animal_Shelter.services;

import com.project.Animal_Shelter.repositories.IDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationService {

    @Autowired
    IDonationRepository iDonationRepository;


}
