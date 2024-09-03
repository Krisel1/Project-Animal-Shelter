package com.project.Animal_Shelter.controllers;


import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.models.User;
import com.project.Animal_Shelter.services.PetService;
import com.project.Animal_Shelter.services.DonationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;
@SpringBootTest
@AutoConfigureMockMvc
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;
    private DonationService donationService;

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    void get_all_pets() throws Exception {
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",false, null, null);
        Pet secondPet = new Pet(2L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",true, null, null);
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
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none", false, null, null);
        when(petService.getPetByID(1L)).thenReturn(pet);
        mockMvc.perform(get("/pets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void get_all_by_user_id() throws Exception {
        User user = new User(1L, "Ivan", "1234", null, null,null, null);
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",false,null, user);
        Pet secondPet = new Pet(2L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",true,null, user);
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
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",false, null, null);
        Pet secondPet = new Pet(2L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",false, null, null);
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
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",true, null, null);
        Pet secondPet = new Pet(2L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",true, null, null);
        petsList.add(pet);
        petsList.add(secondPet);
        when(petService.getAllAnimalsAdopted()).thenReturn(petsList);
        mockMvc.perform(get("/pets/adopted"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }
}

