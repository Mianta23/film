package com.example.film.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Disponibilite
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "idscene")
    private Scene scene = null;

    @ManyToOne
    @JoinColumn(name = "idplateau")
    private Plateau plateau = null;

    @Column(name = "dates")
    private Date dates;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }
}
