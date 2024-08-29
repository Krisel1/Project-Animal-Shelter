package com.project.Animal_Shelter.controllers;

import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.models.User;
import com.project.Animal_Shelter.services.PetService;
import com.project.Animal_Shelter.services.DonationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;
    private DonationService donationService;

    @InjectMocks
    private PetController petController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void get_all_pets() throws Exception {
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none", false, null);
        Pet secondPet = new Pet(2L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none", true, null);
        ArrayList<Pet> petsList = new ArrayList<>();
        petsList.add(pet);
        petsList.add(secondPet);
        when(petService.getAllPets()).thenReturn(petsList);
        mockMvc.perform(get("/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void get_pet_by_ID() throws Exception {
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none", false, null);
        when(petService.getPetByID(1L)).thenReturn(pet);
        mockMvc.perform(get("/pets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void get_all_by_user_id() throws Exception {
        User user = new User(1L, "Ivan", "1234", null);
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none", false, user);
        Pet secondPet = new Pet(2L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none", true, user);
        ArrayList<Pet> petsList = new ArrayList<>();
        petsList.add(pet);
        petsList.add(secondPet);
        when(petService.getAllByUserId(1L)).thenReturn(petsList);
        mockMvc.perform(get("/pets/adopted/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void get_all_animals_without_adopted() throws Exception {
        ArrayList<Pet> petsList = new ArrayList<>();
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none", false, null);
        Pet secondPet = new Pet(2L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none", false, null);
        petsList.add(pet);
        petsList.add(secondPet);
        when(petService.getAllAnimalsWithoutAdopted()).thenReturn(petsList);
        mockMvc.perform(get("/pets/withoutAdopted"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void get_all_animals_adopted() throws Exception {
        ArrayList<Pet> petsList = new ArrayList<>();
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none", true, null);
        Pet secondPet = new Pet(2L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none", true, null);
        petsList.add(pet);
        petsList.add(secondPet);
        when(petService.getAllAnimalsAdopted()).thenReturn(petsList);
        mockMvc.perform(get("/pets/adopted"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    public void testUpdatePet() {
        Long id = 1L;
        Pet pet = new Pet();
        pet.setId(id);
        pet.setDateBirth(LocalDateTime.of(2020, 2, 14, 11, 0));
        pet.setPetName("Neo");
        pet.setDescription("shy");
        pet.setAge("4");
        pet.setSterilized(true);
        pet.setBreed("labrador");
        pet.setPetType("Dog");
        pet.setAdopted(true);

        petService.updatePet(pet, id);

        verify(petService, times(1)).updatePet(pet, id);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void deletePetByIdSuccess() throws Exception {
        Long petId = 1L;

        doNothing().when(petService).deletePet(petId);

        mockMvc.perform(delete("/pets/{id}", petId))
                .andExpect(status().isNoContent());

        verify(petService, times(1)).deletePet(petId);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void deletePetByIdNotFound() throws Exception {
        Long petId = 1L;

        doThrow(new RuntimeException("Pet not found")).when(petService).deletePet(petId);

        mockMvc.perform(delete("/pets/{id}", petId))
                .andExpect(status().isNotFound());

        verify(petService, times(1)).deletePet(petId);
    }
}