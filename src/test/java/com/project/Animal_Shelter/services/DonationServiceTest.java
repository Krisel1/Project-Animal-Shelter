package com.project.Animal_Shelter.services;

import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.repositories.IDonationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class DonationServiceTest {

    @Mock
    private IDonationRepository donationRepository;

    @InjectMocks
    private DonationService donationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void get_all_donations() {
        ArrayList<Donation> donations = new ArrayList<>();
        Donation donation = new Donation(1L, "Vadim", 1000);
        donations.add(donation);
        when(donationRepository.findAll()).thenReturn(donations);
        List<Donation> result = donationService.getAllDonations();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    void get_donation_by_ID() {
        Donation donation = new Donation(1L, "Vadim", 1000);
        when(donationRepository.findById(1L)).thenReturn(Optional.of(donation));
        Donation result = donationService.getDonationByID(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testUpdateDonationService() {
        long id = 1L;
        Donation donation = new Donation();
        donation.setName("Eva");
        donation.setDonation(7);
        donation.setId(id);

        donationService.updateDonation(donation, id);

        verify(donationRepository, times(1)).save(donation);
    }

    @Test
    public void testDeleteDonation_Success() {
        Long donationId = 1L;
        doNothing().when(donationRepository).deleteById(donationId);

        donationService.deleteDonation(donationId);

        verify(donationRepository, times(1)).deleteById(donationId);
    }

    @Test
    public void testDeleteDonation_WhenDonationNotFound() {
        Long donationId = 1L;
        doThrow(new RuntimeException("Donation not found")).when(donationRepository).deleteById(donationId);
        try {
            donationService.deleteDonation(donationId);
        } catch (RuntimeException e) {
            assert(e.getMessage().contains("Donation not found"));
        }
        verify(donationRepository, times(1)).deleteById(donationId);
    }
}