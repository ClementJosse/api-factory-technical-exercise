package com.apifactory.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;

    private Double costAmount;

    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @PrePersist
    public void prePersist() {
        if (startDate == null) startDate = LocalDate.now();
        updateDate = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        updateDate = LocalDate.now();
    }
}
