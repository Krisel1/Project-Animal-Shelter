package com.project.Animal_Shelter.services;

import com.project.Animal_Shelter.repositories.IDonationRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DonationServiceTest {

    @InjectMocks
    private DonationService donationService;

    @Mock
    private IDonationRepository iDonationRepository;

}
