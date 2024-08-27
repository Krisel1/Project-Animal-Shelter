package com.project.Animal_Shelter.services;

import com.project.Animal_Shelter.models.Donation;
import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.models.User;
import com.project.Animal_Shelter.repositories.IPetRepository;
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

import java.time.LocalDateTime;

public class PetServiceTest {

    @InjectMocks
    private PetService petService;

    @Mock
    private IPetRepository iPetRepository;
    private Pet pet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void get_all_pets() {
        ArrayList<Pet> pets = new ArrayList<>();
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",false, null);
        pets.add(pet);
        when(iPetRepository.findAll()).thenReturn(pets);
        List<Pet> result = petService.getAllPets();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }
    @Test
    void get_pet_by_ID() {
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",false, null);
        when(iPetRepository.findById(1L)).thenReturn(Optional.of(pet));
        Pet result = petService.getPetByID(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }
    @Test
    void get_all_pets_without_adopted() {
        ArrayList<Pet> petsList = new ArrayList<>();
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",false, null);
        Pet secondPet = new Pet(2L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",true, null);
        Pet thirdPet = new Pet(3L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",false, null);
        petsList.add(pet);
        petsList.add(secondPet);
        petsList.add(thirdPet);
        when(iPetRepository.findAll()).thenReturn(petsList);
        List<Pet> result = petService.getAllAnimalsWithoutAdopted();
        assertNotNull(result);
        assertEquals(3, petsList.size());
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(3L, result.get(1).getId());
    }
    @Test
    void get_pest_by_user_ID() {
        ArrayList<Pet> petsList = new ArrayList<>();
        User firstUser = new User(1L, "Juan", "1234", null);
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",false, firstUser);
        Pet thirdPet = new Pet(2L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",false, firstUser);
        petsList.add(pet);
        petsList.add(thirdPet);
        when(iPetRepository.findByUserId(1L)).thenReturn(petsList);
        List<Pet> result = iPetRepository.findByUserId(1L);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
    }
    @Test
    void get_all_pets_adopted() {
        ArrayList<Pet> petsList = new ArrayList<>();
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",false, null);
        Pet secondPet = new Pet(2L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",true, null);
        petsList.add(pet);
        petsList.add(secondPet);
        when(iPetRepository.findAll()).thenReturn(petsList);
        List<Pet> result = petService.getAllAnimalsAdopted();
        assertNotNull(result);
        assertEquals(2, petsList.size());
        assertEquals(1, result.size());
        assertEquals(2L, result.get(0).getId());
    }
    @Test
    public void testUpdateDonation() {

        long id = 1L;
        Pet pet = new Pet();
        pet.setId(id);
        pet.setDateBirth(LocalDateTime.of(2008,3,31,11,0));
        pet.setPetName("Ulises");
        pet.setDescription("defending family");
        pet.setAge("12");
        pet.setSterilized(false);
        pet.setBreed("dalmatian");
        pet.setPetType("Dog");
        pet.setAdopted(true);


        petService.updatePet(pet, id);

        assert(pet.getId() == id);


        verify(iPetRepository, times(1)).save(pet);
    }
}
