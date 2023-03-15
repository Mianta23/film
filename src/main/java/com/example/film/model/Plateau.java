package com.example.film.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Plateau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idplateau", nullable = false)
    private int id;

    @Column(name = "nom_plateau")
    private String nomPlateau = null;

    @Column(name="x")
    private double x;

    @Column(name="y")
    private double y;

    @Transient
    private List<Scene> listScene;

    @Transient
    private int nombre = 0;
    public boolean isValable(List<Scene> scenePl,Scene scene){
        boolean is = true;
        int i = 0;
        if(scenePl.size() > 0){
            System.out.println("pl : "+scenePl.get(0).getId()+" "+scene.getId());
            for(i = 0 ; i < scenePl.size() ; i++){
                if(scene.getDebut().toLocalTime().isBefore(scenePl.get(i).getDebut().toLocalTime().plusSeconds(scenePl.get(i).getFin().toLocalTime().toSecondOfDay())) == true && scene.getDebut().toLocalTime().isAfter(scenePl.get(i).getDebut().toLocalTime()) == true){
                    is = false;
                    break;
                }
            }
        }

        return is;
    }

    public LocalDate planeListScenne(LocalDate debut, LocalDate fin,List<Scene> list){
        debut = this.plannifier(debut,fin,this.listScene,list);
        return debut;
    }

    public LocalDate plannifier(LocalDate debut, LocalDate fin,List<Scene> list,List<Scene> stock){
        int i = 0;
        List<Scene> scenePl = new ArrayList<>();
        List<Scene> non = new ArrayList<>();
        Scene sc = null;
        if(list.size() > 0 && (debut.isBefore(fin) == true || debut.equals(fin) == true)){
            for(i = 0 ; i < list.size() ; i ++){
                if(this.isValable(scenePl,list.get(i)) == true){
                    sc = list.get(i);
                    sc.setDates(debut);
                    stock.add(sc);
                    scenePl.add(sc);
                }else{
                    non.add(list.get(i));
                }
            }
            debut = debut.plusDays(1);
            debut = this.plannifier(debut,fin,non,stock);
        }
        return debut;
    }


    public void renouveller(){
        this.listScene = new ArrayList<>();
    }
    public List<Scene> getListScene() {
        return listScene;
    }

    public void setListScene(Scene scene) {
        this.listScene.add(scene);
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomPlateau() {
        return nomPlateau;
    }
    public void setNomPlateau(String nomPlateau) {
        this.nomPlateau = nomPlateau;
    }
    public Plateau() {
        listScene = new ArrayList<>();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
