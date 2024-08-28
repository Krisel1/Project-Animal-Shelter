package com.project.Animal_Shelter.controllers;


import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.services.DonationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(DonationController.class)
public class DonationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DonationService donationService;

    @InjectMocks
    private DonationController donationController;
    private Donation donation;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void get_all_donation() throws Exception {
        Donation donation = new Donation(1L, "Vadim", 1000);
        Donation secondDonation = new Donation(2L, "Stas", 500);
        ArrayList<Donation> donations = new ArrayList<>();
        donations.add(donation);
        donations.add(secondDonation);
        when(donationService.getAllDonations()).thenReturn(donations);
        mockMvc.perform(get("/donations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void get_donation_by_ID() throws Exception{
        Donation donation = new Donation(1L, "Vadim", 1000);
        when(donationService.getDonationByID(1L)).thenReturn(donation);
        mockMvc.perform(get("/donations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Vadim"))
                .andExpect(jsonPath("$.donation").value(1000));
    }
    @Test
    public void testUpdateDonation() {

        Long id = 1L;
        Donation donation = new Donation();
        donation.setId(id);
        donation.setName("pepe");
        donation.setDonation(23);

        donationService.updateDonation(donation, id);

        verify(donationService, times(1)).updateDonation(donation, id);
    }
}
