package com.project.Animal_Shelter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.models.User;
import com.project.Animal_Shelter.services.DonationService;
import com.project.Animal_Shelter.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;
    private DonationService donationService;

    @InjectMocks
    private PetController petController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Register JavaTimeModule for LocalDateTime serialization
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

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
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void adopt() throws Exception {
        Pet pet = new Pet(5L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", true, "none", "none", true, null, null);
        when(petService.adopt(5L, 3L)).thenReturn(pet);
        mockMvc.perform(post("/pets/adopt/5")
                        .param("user_id", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5L))
                .andExpect(jsonPath("$.adopted").value(true));
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
    void testCreatePet() throws Exception {
        Pet pet = new Pet();
        pet.setDateBirth(LocalDateTime.of(2024, 7, 23, 10, 0));
        pet.setPetName("Buddy");
        pet.setDescription("Friendly dog");
        pet.setAge("2");
        pet.setSterilized(true);
        pet.setBreed("Labrador");
        pet.setPetType("Dog");
        pet.setAdopted(false);

        when(petService.createPet(any(Pet.class))).thenAnswer(invocation -> {
            Pet petCreated = invocation.getArgument(0);
            petCreated.setId(1L);
            return petCreated;
        });

        String petJson = objectMapper.writeValueAsString(pet);

        mockMvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(petJson)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.petName").value("Buddy"))
                .andExpect(jsonPath("$.description").value("Friendly dog"))
                .andExpect(jsonPath("$.age").value("2"))
                .andExpect(jsonPath("$.sterilized").value(true))
                .andExpect(jsonPath("$.breed").value("Labrador"))
                .andExpect(jsonPath("$.petType").value("Dog"))
                .andExpect(jsonPath("$.adopted").value(false));
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