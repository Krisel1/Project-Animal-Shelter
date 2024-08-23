package com.project.Animal_Shelter.services;

import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.repositories.IPetRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class PetServiceTest {

    @InjectMocks
    private PetService petService;

    @Mock
    private IPetRepository iPetRepository;
    private Pet pet;
}
