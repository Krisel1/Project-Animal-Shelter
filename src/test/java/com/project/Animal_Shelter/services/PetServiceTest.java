package com.project.Animal_Shelter.services;

import com.project.Animal_Shelter.repositories.IPetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class PetServiceTest {

    @InjectMocks
    private PetService petService;

    @Mock
    private IPetRepository iPetRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deletePetSuccess() {
        Long petId = 1L;

        doNothing().when(iPetRepository).deleteById(petId);

        petService.deletePet(petId);

        verify(iPetRepository, times(1)).deleteById(petId);
    }

    @Test
    void deletePetNotFound() {
        Long petId = 1L;

        doThrow(new RuntimeException("Pet not found")).when(iPetRepository).deleteById(petId);

        try {
            petService.deletePet(petId);
        } catch (Exception e) {
            assertEquals("Pet not found", e.getMessage());
        }

        verify(iPetRepository, times(1)).deleteById(petId);
    }

}
