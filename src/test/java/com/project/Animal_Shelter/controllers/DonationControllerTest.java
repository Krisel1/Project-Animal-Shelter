package com.project.Animal_Shelter.controllers;

import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.models.User;
import com.project.Animal_Shelter.services.DonationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.services.DonationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DonationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DonationService donationService;

    @InjectMocks
    private DonationController donationController;
    private Donation donation;

    @Test
    @WithMockUser(username = "juan", roles = {"ADMIN"})
    void get_all_donation() throws Exception {
        Donation donation = new Donation(1L, "Vadim", 1000, null);
        Donation secondDonation = new Donation(2L, "Stas", 500, null);
        List<Donation> donations = new ArrayList<>();
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
    void get_donation_by_ID() throws Exception {
        Donation donation = new Donation(1L, "Vadim", 1000, null);

        when(donationService.getDonationByID(1L)).thenReturn(donation);

        mockMvc.perform(get("/donations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Vadim"))
                .andExpect(jsonPath("$.donation").value(1000));
    }
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void get_all_by_user_id() throws Exception {
        ArrayList<Donation> donationList = new ArrayList<>();
        User firstUser = new User(1L, "Juan", "1234", null,null, null, null);
        Donation donation = new Donation(1L, "Amigo",500, firstUser);
        Donation secondDonation = new Donation(2L, "Pet",400, firstUser);
        donationList.add(donation);
        donationList.add(secondDonation);
        when(donationService.getAllByUserId(1L)).thenReturn(donationList);
        mockMvc.perform(get("/donations/getAllByUser/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }
}
