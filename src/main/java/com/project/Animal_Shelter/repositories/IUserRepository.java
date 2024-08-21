package com.project.Animal_Shelter.repositories;

import com.project.Animal_Shelter.models.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Long> {
}
