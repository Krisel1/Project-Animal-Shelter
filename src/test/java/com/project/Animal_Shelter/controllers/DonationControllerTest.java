package com.project.Animal_Shelter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.services.DonationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(donationController).build();
    }

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
    void get_donation_by_ID() throws Exception {
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

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void delete_donation_by_id_success() throws Exception {
        Long donationId = 1L;

        doNothing().when(donationService).deleteDonation(donationId);

        mockMvc.perform(delete("/donations/{id}", donationId))
                .andExpect(status().isNoContent());

        verify(donationService, times(1)).deleteDonation(donationId);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void delete_donation_by_id_not_found() throws Exception {
        Long donationId = 1L;

        doThrow(new RuntimeException("Donation not found")).when(donationService).deleteDonation(donationId);

        mockMvc.perform(delete("/donations/{id}", donationId))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testCreateDonation() throws Exception {
        Donation donation = new Donation(1L, "Vadim", 1000);

        when(donationService.createDonation(any(Donation.class))).thenReturn(donation);

        String donationJson = objectMapper.writeValueAsString(donation);

        mockMvc.perform(post("/donations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(donationJson)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Vadim"))
                .andExpect(jsonPath("$.donation").value(1000));
    }
}