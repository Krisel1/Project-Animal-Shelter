package com.project.Animal_Shelter.controllers;

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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DonationController.class)
public class DonationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DonationService donationService;

    @InjectMocks
    private DonationController donationController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(donationController).build();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void delete_donation_by_id_success() throws Exception {
        Long donationId = 1L;

        doNothing().when(donationService).deleteDonation(donationId);

        mockMvc.perform(delete("/donations/{id}", donationId));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void delete_donation_by_id_not_found() throws Exception {
        Long donationId = 1L;

        doThrow(new RuntimeException("Donation not found")).when(donationService).deleteDonation(donationId);

        mockMvc.perform(delete("/donations/{id}", donationId))
                .andExpect(status().isNotFound());
    }
}
