package com.example.voitures;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Clients {
    @Id
    private int id;
    private String nom;
    private String prenom;
    @OneToMany
    @JoinColumn(name = "clients_id")
    private List<Voitures> voitures;
}
