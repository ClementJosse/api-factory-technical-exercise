package com.apifactory.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter      // Génère tous les getters
@Setter      // Génère tous les setters
@NoArgsConstructor // Génère un constructeur sans paramètres
public abstract class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @Email
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Contract> contracts;
}
