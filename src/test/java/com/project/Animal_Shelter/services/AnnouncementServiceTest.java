package com.project.Animal_Shelter.services;

import com.project.Animal_Shelter.models.Announcement;
import com.project.Animal_Shelter.repositories.IAnnouncementRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class AnnouncementServiceTest {

    @InjectMocks
    private AnnouncementService announcementService;

    @Mock
    private IAnnouncementRepository iAnnouncementRepository;
    private Announcement announcement;
}
