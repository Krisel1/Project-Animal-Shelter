package com.project.Animal_Shelter.repositories;

import com.project.Animal_Shelter.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
