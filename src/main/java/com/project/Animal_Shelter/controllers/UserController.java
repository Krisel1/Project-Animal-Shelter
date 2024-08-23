package com.project.Animal_Shelter.controllers;

import com.project.Animal_Shelter.models.User;
import com.project.Animal_Shelter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @PutMapping(path = "/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Long id) {
        userService.updateUser(user, id);
    }
}
