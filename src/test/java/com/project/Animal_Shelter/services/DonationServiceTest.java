package com.project.Animal_Shelter.services;

import com.project.Animal_Shelter.repositories.IDonationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DonationServiceTest {

    @Mock
    private IDonationRepository donationRepository;

    @InjectMocks
    private DonationService donationService;

    @Test
    public void testDeleteDonation_Success() {
        Long donationId = 1L;
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