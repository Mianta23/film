package com.example.film.model;

import javax.persistence.*;

@Entity
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaction", nullable = false)
    private int id;
    @Column(name = "designation")
    private String nomType = null;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomType() {
        return nomType;
    }
    public void setNomType(String nomType) {
        this.nomType = nomType;
    }
    public Action() {
    }
}