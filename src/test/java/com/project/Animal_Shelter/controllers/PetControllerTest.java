package com.project.Animal_Shelter.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.services.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.project.Animal_Shelter.models.Donation;
//import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.models.User;
//import com.project.Animal_Shelter.services.PetService;
//import com.project.Animal_Shelter.services.DonationService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.security.test.context.support.WithMockUser;
import java.util.ArrayList;
//import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.Mockito.*;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;
@WebMvcTest(PetController.class)
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;
    //private DonationService donationService;

    @Autowired
    private ObjectMapper objectMapper; // Añadir ObjectMapper


    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void get_all_pets() throws Exception {
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",false, null);
        Pet secondPet = new Pet(2L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",true, null);
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
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",false, user);
        Pet secondPet = new Pet(2L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",true, user);
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
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",false, null);
        Pet secondPet = new Pet(2L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",false, null);
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
        Pet pet = new Pet(1L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",true, null);
        Pet secondPet = new Pet(2L, LocalDateTime.of(2024, 7, 23, 10, 0), "Amigo", "none", "1", false, "none", "none",true, null);
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
        pet.setDateBirth(LocalDateTime.of(2020,2,14,11,0));
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
    void testCreatePet() throws Exception {
        // Preparar datos de prueba
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setDateBirth(LocalDateTime.of(2024, 7, 23, 10, 0));
        pet.setPetName("Buddy");
        pet.setDescription("Friendly dog");
        pet.setAge("2");
        pet.setSterilized(true);
        pet.setBreed("Labrador");
        pet.setPetType("Dog");
        pet.setAdopted(false);

        // Mockear el comportamiento del servicio
        when(petService.createPet(any(Pet.class))).thenReturn(pet);

        // Convertir el objeto Pet a JSON
        String petJson = objectMapper.writeValueAsString(pet);

        // Realizar la petición POST al controlador
        mockMvc.perform(post("/pets{id}", 1) // El endpoint es "/pets/{id}"
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(petJson) // Usamos el JSON como contenido de la petición
                        .with(csrf())) // Incluir un token CSRF simulado
                .andExpect(status().isOk()) // Esperamos un código 200 OK
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.petName").value("Buddy"))
                .andExpect(jsonPath("$.description").value("Friendly dog"))
                .andExpect(jsonPath("$.age").value("2"))
                .andExpect(jsonPath("$.sterilized").value(true))
                .andExpect(jsonPath("$.breed").value("Labrador"))
                .andExpect(jsonPath("$.petType").value("Dog"))
                .andExpect(jsonPath("$.adopted").value(false));
    }
}

