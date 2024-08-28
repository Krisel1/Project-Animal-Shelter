package com.project.Animal_Shelter.services;

import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.repositories.IDonationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class DonationServiceTest {

    @InjectMocks
    private DonationService donationService;

    @Mock
    private IDonationRepository iDonationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void get_all_donations() {
        ArrayList<Donation> donations = new ArrayList<>();
        Donation donation = new Donation(1L, "Vadim", 1000);
        donations.add(donation);
        when(iDonationRepository.findAll()).thenReturn(donations);
        List<Donation> result = donationService.getAllDonations();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }
    @Test
    void get_donation_by_ID() {
        Donation donation = new Donation(1L, "Vadim", 1000);
        when(iDonationRepository.findById(1L)).thenReturn(Optional.of(donation));
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


        donationService.updateDonation(donation, id);

        assert(donation.getId() == id);


        verify(iDonationRepository, times(1)).save(donation);
    }

    @Test

    void testCreateDonation() {

        Donation donation = new Donation();
        donation.setName("John");
        donation.setDonation(500);

        when(iDonationRepository.save(donation)).thenAnswer(invocation -> {
            Donation savedDonation = invocation.getArgument(0);
            savedDonation.setId(1L); // o cualquier otro valor único generado automáticamente
            return savedDonation;

        });

        Donation createdDonation = donationService.createDonation(donation);
        assertNotNull(createdDonation);
        assertEquals(1L, createdDonation.getId());
        assertEquals("John", createdDonation.getName());
        assertEquals(500, createdDonation.getDonation());

    }

}
