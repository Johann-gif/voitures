package com.example.voitures;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Adresses {
    @Id
    private int id;
    private String adresse;
}
