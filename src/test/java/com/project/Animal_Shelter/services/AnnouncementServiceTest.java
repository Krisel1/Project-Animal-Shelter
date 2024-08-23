package com.project.Animal_Shelter.services;

import com.project.Animal_Shelter.models.Pet;
import com.project.Animal_Shelter.repositories.IPetRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class AnnouncementServiceTest {

    @InjectMocks
    private PetService announcementService;

    @Mock
    private IPetRepository iAnnouncementRepository;
    private Pet announcement;
}
