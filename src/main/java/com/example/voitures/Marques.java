package com.example.voitures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Marques {
    @Id
    private int id;
    private String nom;
    @OneToMany
    @JoinColumn(name = "marques_id")
    private List<Voitures> voitures;
    @ManyToMany(mappedBy = "marques")
    @JsonIgnoreProperties("marques")
    private List<Concessionnaires> concessionnaires;
}
