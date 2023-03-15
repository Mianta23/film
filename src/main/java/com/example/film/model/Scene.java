package com.example.film.model;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Scene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idscene", nullable = false)
    private int id;
    @Column(name = "nom_scene")
    private String nomScene = null;
    @ManyToOne
    @JoinColumn(name = "idplateau")
    private Plateau plateau = null;

    @ManyToOne
    @JoinColumn(name = "idfilm")
    private Film film = null;

    @Column(name = "debut")
    private Time debut;

    @Column(name = "fin")
    private Time fin;

    @Transient
    private LocalDate dates;


    public LocalDate getDates() {
        return dates;
    }

    public void setDates(LocalDate dates) {
        this.dates = dates;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomScene() {
        return nomScene;
    }
    public void setNomScene(String nomScene) {
        this.nomScene = nomScene;
    }
    public Plateau getPlateau() {
        return plateau;
    }
    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Time getDebut() {
        return debut;
    }

    public void setDebut(Time debut) {
        this.debut = debut;
    }

    public Time getFin() {
        return fin;
    }

    public void setFin(Time fin) {
        this.fin = fin;
    }

    public Scene() {
    }

}
