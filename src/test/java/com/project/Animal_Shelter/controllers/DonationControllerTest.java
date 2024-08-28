package com.project.Animal_Shelter.controllers;

import com.project.Animal_Shelter.services.DonationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DonationController.class)
public class DonationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DonationService donationService;

    @InjectMocks
    private DonationController donationController;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void delete_donation_by_ID_Success() throws Exception {
        // Arrange
        long donationId = 1L;
        when(donationService.deleteDonation(donationId)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/donations/{id}", donationId))
                .andExpect(status().isNoContent());

        verify(donationService, times(1)).deleteDonation(donationId);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void delete_donation_by_ID_NotFound() throws Exception {
        // Arrange
        long donationId = 1L;
        when(donationService.deleteDonation(donationId)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(delete("/donations/{id}", donationId))
                .andExpect(status().isNotFound());

        verify(donationService, times(1)).deleteDonation(donationId);
    }
}