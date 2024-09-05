package com.project.Animal_Shelter.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "adopted")
    private boolean adopted;
    @Column(name = "url")
    private String url;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id", nullable = true)
    @JsonBackReference
    private User user;


}

