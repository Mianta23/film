package com.example.film.model;;

import java.sql.Time;
import javax.persistence.*;

@Entity
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero", nullable = false)
    private int numero;

    @ManyToOne
    @JoinColumn(name = "idscene")
    private Scene scene = null;

    @ManyToOne
    @JoinColumn(name = "idpers")
    private Personnage personnage = null;

    @ManyToOne
    @JoinColumn(name = "idaction")
    private Action action = null;

    @Column (name = "dialogue")
    private String description = null;

    @Column (name = "duree")
    private Time duree = null;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Scene getScene() {
        return scene;
    }
    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public Personnage getPersonnage() {
        return personnage;
    }
    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
    }
    public Action getAction() {
        return action;
    }
    public void setType(Action action) {
        this.action = action;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Time getDuree() {
        return duree;
    }
    public void setDuree(Time duree) {


        this.duree = duree;
    }
    public Tache() {
    }
}
