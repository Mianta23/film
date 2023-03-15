package com.example.film.model;

import com.example.film.dao.HibernateDAO;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tournage{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtournage", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "idfilm")
    private Film film;
    @Column(name = "tournage")
    private String tournage;

    @Column(name="date")
    private Timestamp date;

    public ArrayList<Scene> planning(String debut,String fin,HibernateDAO dao){
        ArrayList<Scene> list = new ArrayList<>();
        List<Scene> cesScene = dao.findQuery(Scene.class,"select * from vscene where idtournage="+this.id+" order by idplateau,debut asc");
        int i =0;
        ArrayList<Plateau> listPlateau = this.plannifierScene(cesScene,list);
        Plateau p = Tournage.maxScene(listPlateau,new ArrayList<Plateau>());
        ArrayList<Plateau> stocker = new ArrayList<>();
        this.plannificationPlateau(LocalDate.parse(debut),LocalDate.parse(fin),p,listPlateau,stocker,list);
        return list;
    }

    public void plannificationPlateau(LocalDate debut , LocalDate fin, Plateau p, ArrayList<Plateau> list, ArrayList<Plateau> stocker, List<Scene> scene){
        if(p != null){
            debut = p.planeListScenne(debut,fin,scene);
            stocker.add(p);
            this.plannificationPlateau(debut,fin,this.plateauAkaiky(p,list,stocker),list,stocker,scene);
        }
    }

    public Plateau plateauAkaiky(Plateau plateau,ArrayList<Plateau> list,ArrayList<Plateau> stocker){
        int i = 0, g = 0;
        Plateau p = null;
        for(i = 0 ; i < list.size() ; i++){
            if(Tournage.isExiste(list.get(i),stocker) == false){
                p = list.get(i);
                g = i;
                break;
            }
        }
        if(p != null){
            for(i = 0 ; i < list.size() ; i++){
                if(Tournage.isExiste(list.get(i),stocker) == false  && Tournage.calculDistance(p,plateau) > Tournage.calculDistance(plateau,list.get(i))){
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






    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }

    public String getTournage() {
        return tournage;
    }
    public void setTournage(String tournage) {
        this.tournage = tournage;
    }

    public Timestamp getDate() {
        return date;
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }
}