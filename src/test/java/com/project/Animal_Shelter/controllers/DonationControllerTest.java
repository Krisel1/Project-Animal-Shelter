package com.project.Animal_Shelter.controllers;


import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.services.DonationService;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class DonationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DonationService donationService;

    @InjectMocks
    private DonationController donationController;
    private Donation donation;

}
