package com.example.film.model;


import com.example.film.dao.HibernateDAO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "film")
    private String film;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }
    public ArrayList<Scene> planning(String debut, String fin, HibernateDAO dao,String[] lis){
        ArrayList<Scene> list = new ArrayList<>();
        List<Scene> cesScene = this.listScene(dao,lis);
        int i =0;
        ArrayList<Plateau> listPlateau = this.plannifierScene(cesScene,list);
        Plateau p = Tournage.maxScene(listPlateau,new ArrayList<Plateau>());
        ArrayList<Plateau> stocker = new ArrayList<>();
        LocalDate deb = LocalDate.parse(debut);
        LocalDate f = LocalDate.parse(fin);
        ArrayList<Plateau> lir = new ArrayList<>();
        this.plannificationPlateau(dao,deb,f,p,listPlateau,stocker,list,lir);
        return list;
    }

    public List<Scene> listScene(HibernateDAO dao,String[] list){
        List<Scene> scene = new ArrayList<>();
        int i = 0;
        for(i = 0 ; i < list.length ; i ++){
            scene.add(dao.findById(Scene.class,Integer.parseInt(list[i])));
        }
        return scene;
    }

    public void plannificationPlateau(HibernateDAO dao,LocalDate debut , LocalDate fin, Plateau p, ArrayList<Plateau> list, ArrayList<Plateau> stocker, List<Scene> scene,ArrayList<Plateau> lir) {

        if (p != null && (fin.isEqual(debut) == true || debut.isBefore(fin) ==true)) {
            p.defDisponibilite(dao);
            if (p.isDisponible(debut, dao) == true) {
                debut = p.planeListScenne(debut, fin, scene);
                stocker.add(p);
            } else {
                lir.add(p);
                p = this.returnPlateau(list,lir,stocker);
                if(p != null){
                    this.plannificationPlateau(dao, debut, fin, p, list, stocker, scene, lir);
                }else{
                    lir = new ArrayList<>();
                    debut = debut.plusDays(1);
                    p = this.returnPlateau(list,lir,stocker);
                    this.plannificationPlateau(dao, debut, fin, p, list, stocker, scene, lir);
                }
            }
            this.plannificationPlateau(dao, debut, fin, this.plateauAkaiky(p, list, stocker), list, stocker, scene, lir);
        }
    }

    public Plateau returnPlateau(ArrayList<Plateau> listPla,ArrayList<Plateau> lir,ArrayList<Plateau> stocker){
        Plateau p = null;
        int i = 0;
        boolean is = true;
        for(i = 0 ; i < listPla.size() ; i++){
            if(Tournage.isExiste(listPla.get(i),lir) == false && Tournage.isExiste(listPla.get(i),stocker) == false){
                p = listPla.get(i);
                break;
            }
        }
        return p;
    }

    public Plateau plateauAkaiky(Plateau plateau,ArrayList<Plateau> list,ArrayList<Plateau> stocker){
        int i = 0, g = 0;
        Plateau p = null;
        for(i = 0 ; i < list.size() ; i++){
            if(Tournage.isExiste(list.get(i),stocker) == false ){
                p = list.get(i);
                g = i;
                break;
            }
        }
        if(p != null){
            for(i = 0 ; i < list.size() ; i++){
                if(  Tournage.isExiste(list.get(i),stocker) == false  && Tournage.calculDistance(p,plateau) > Tournage.calculDistance(plateau,list.get(i))){
                    p = list.get(i);
                }
            }
        }

        return p;
    }

    public static double calculDistance(Plateau p , Plateau p2){
        return Math.sqrt((p.getX() - p2.getX()) * (p.getX() - p2.getX()) - (p.getY() - p2.getY()) * (p.getY() - p2.getY()) );
    }




    public ArrayList<Plateau> plannifierScene(List<Scene> list, ArrayList<Scene> stock){
        int i = 0;
        List<Scene> non = new ArrayList<>();
        List<Scene> oui = new ArrayList<>();
        Plateau p = null;
        int g = 0;
        ArrayList<Plateau> listPlateau = new ArrayList<>();
        ArrayList<Plateau> stockPlateau = new ArrayList<>();
        if(list.size() > 0){
            p = list.get(0).getPlateau();
            p.renouveller();
            for(i = 0 ; i < list.size() ; i++){
                if(p.getId() == list.get(i).getPlateau().getId()){
                    p.setListScene(list.get(i));
                }else{
                    listPlateau.add(p);
                    p = list.get(i).getPlateau();
                    p.renouveller();
                    i--;
                }
            }
            listPlateau.add(p);
        }
        return listPlateau;
    }


    public static Plateau maxScene(ArrayList<Plateau> list,ArrayList<Plateau> stock){
        int i = 0,g=0;
        Plateau p = null;
        for(i = 0 ; i < list.size() ; i++){
            if(Tournage.isExiste(list.get(i),stock) == false){
                p = list.get(i);
                g = i;
                break;
            }
        }
        if(p != null){
            for(i = g ; i < list.size() ; i++){
                if(p.getListScene().size() < list.get(i).getListScene().size()){
                    p = list.get(i);
                }
            }
        }
        return p;
    }

    public static boolean isExiste(Plateau p,ArrayList<Plateau> list){
        int i = 0;
        boolean is = false;
        for(i = 0 ; i < list.size() ; i++){
            if(list.get(i).getId() == p.getId()){
                is = true;
                break;
            }
        }
        return is;
    }

}
