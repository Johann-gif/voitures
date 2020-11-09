package com.example.voitures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Concessionnaires {
    @Id
    private int id;
    private String nom;
    @OneToMany
    @JoinColumn(name = "concessionnaires_id")
    private List<Adresses> adresses;
    @ManyToMany
    @JoinTable(
            name = "concessionnaires_marques",
            joinColumns = @JoinColumn(name = "concessionnaires_id"),
            inverseJoinColumns = @JoinColumn(name = "marques_id")
    )
    @JsonIgnoreProperties("concessionnaires")
    private List<Marques> marques;
}
