package com.project.Animal_Shelter.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dateBirth")
    private LocalDateTime dateBirth;

    @Column(name = "petName")
    private String petName;

    @Column(name = "description")
    private String description;

    @Column(name = "age")
    private String age;

    @Column(name = "Sterilized")
    private boolean Sterilized;

    @Column(name = "breed")
    private String breed;

    @Column(name = "petType")
    private String petType;


}

