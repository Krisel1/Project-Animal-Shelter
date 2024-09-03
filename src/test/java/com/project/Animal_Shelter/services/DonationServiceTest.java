package com.project.Animal_Shelter.services;

import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.models.User;
import com.project.Animal_Shelter.repositories.IDonationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
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
        Donation donation = new Donation(1L, "Vadim", 1000, null);
        donations.add(donation);
        when(iDonationRepository.findAll()).thenReturn(donations);
        List<Donation> result = donationService.getAllDonations();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }
    @Test
    void get_donation_by_ID() {
        Donation donation = new Donation(1L, "Vadim", 1000, null);
        when(iDonationRepository.findById(1L)).thenReturn(Optional.of(donation));
        Donation result = donationService.getDonationByID(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }
    @Test
    void get_all_by_user_id() {
        ArrayList<Donation> donationList = new ArrayList<>();
        User firstUser = new User(1L, "Juan", "1234", null,null, null, null);
        Donation donation = new Donation(1L, "Amigo",500, firstUser);
        Donation thirdDonation = new Donation(2L, "Pet",400, firstUser);
        donationList.add(donation);
        donationList.add(thirdDonation);
        when(iDonationRepository.findByUserId(1L)).thenReturn(donationList);
        List<Donation> result = iDonationRepository.findByUserId(1L);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
    }


}
