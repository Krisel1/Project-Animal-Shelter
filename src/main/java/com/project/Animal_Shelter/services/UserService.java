package com.project.Animal_Shelter.services;


import com.project.Animal_Shelter.models.User;
import com.project.Animal_Shelter.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserRepository iUserRepository;

    public void updateUser(User user, long id) {
        user.setId(id);
        iUserRepository.save(user);
    }
}
