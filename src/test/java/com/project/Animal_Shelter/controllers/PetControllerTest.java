package com.project.Animal_Shelter.controllers;

import com.project.Animal_Shelter.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetController.class)
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @InjectMocks
    private PetController petController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void deletePetByIdSuccess() throws Exception {
        Long petId = 1L;

        doNothing().when(petService).deletePet(petId);

        mockMvc.perform(delete("/pets/{id}", petId));

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