package com.apifactory.backend.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company extends Client {
    private String companyIdentifier; // ex: aaa-123
}
