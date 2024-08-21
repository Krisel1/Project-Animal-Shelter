package com.project.Animal_Shelter.services;

import com.project.Animal_Shelter.repositories.IAnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementService {

    @Autowired
    IAnnouncementRepository iAnnouncementRepository;

}
