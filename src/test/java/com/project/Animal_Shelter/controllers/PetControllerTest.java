package com.project.Animal_Shelter.controllers;


import com.project.Animal_Shelter.services.PetService;
import com.project.Animal_Shelter.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService announcementService;
    private DonationService donationService;


}

