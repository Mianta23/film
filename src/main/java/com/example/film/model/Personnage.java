package com.example.film.model;

import javax.persistence.*;

@Entity
public class Personnage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpers", nullable = false)
    private int id;

    @Column(name = "nom")
    private String nomPersonnage = null;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomPersonnage() {
        return nomPersonnage;
    }
    public void setNomPersonnage(String nomPersonnage) {
        this.nomPersonnage = nomPersonnage;
    }
    public Personnage() {
    }
}
