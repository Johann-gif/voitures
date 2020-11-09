package com.example.voitures;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Voitures {
    @Id
    private int id;
    private String serie;

    /* Ne fonctionne pas pour le put/post, comment faire pour modifier ou ajouter une donnée avec des clés étrangères ?
    public void setMarques_id(int id) {
        this.marques_id = id;
    }

    public void setClients_id(int id) {
        this.clients_id = id;
    }*/
}
