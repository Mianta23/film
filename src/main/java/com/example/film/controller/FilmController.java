package com.example.film.controller;

import com.example.film.model.*;
import com.example.film.dao.HibernateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


@Controller
public class FilmController
{
    @Autowired
    HibernateDAO dao;

    //Film
    @GetMapping("ListeFilm")
    private String ListeFilm(Model model)
    {
        model.addAttribute("film",dao.findAll(Film.class));
        return "Film";
    }

    @PostMapping("newFilm")
    private Object newFilm(@ModelAttribute Film film)
    {
        dao.create(film);
        return new RedirectView("ListeFilm");
    }


    //Tournage
    @GetMapping("/tournage")
    private String tournage()
    {
        return "Tournage";
    }

    @GetMapping("/listTournage")
    private Object getTournageById(@RequestParam int id,Model model)
    {
        model.addAttribute("tournage",dao.findQuery(Tournage.class,"select * from tournage where idfilm="+id));
        return "Tournage";
    }

    @PostMapping("/newTournage")
    private Object newTournage(@ModelAttribute Tournage tournage, HttpServletRequest request)
    {
        Integer id = Integer.parseInt(request.getParameter("idfilm"));
        Film film = new Film();
        film.setId(id);
        tournage.setFilm(film);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("datetournage"),formatter);
        Timestamp timestamp = Timestamp.valueOf(dateTime);

        tournage.setDate(timestamp);
        dao.create(tournage);
        return new RedirectView("listTournage?id="+id);
    }

    //plateau liste
    @GetMapping("liste-scene")
    private String getSceneById(@RequestParam int id,Model model,HttpServletRequest request)
    {
        model.addAttribute("plateau",dao.findAll(Plateau.class));
        model.addAttribute("scene",dao.findQuery(Scene.class,"select * from scene where idfilm="+id));
        request.setAttribute("id",id);
        return "Scene";
    }

    //Scene
    @PostMapping("/new-scene")
    private Object newScene(@ModelAttribute Scene scene, HttpServletRequest request)
    {
        Integer idfilm = Integer.valueOf(request.getParameter("idfilm"));
        Film t = new Film();
        t.setId(idfilm);

        Integer idPlateau = Integer.valueOf(request.getParameter("idplateau"));
        Plateau p = new Plateau();
        p.setId(idPlateau);

        scene.setFilm(t);
        scene.setPlateau(p);
        dao.create(scene);
        return new RedirectView("liste-scene?id="+idfilm);
    }

    //Liste tache & liste Personnage & action
    @GetMapping("/liste-tache")
    private String listeTache(@RequestParam int id,Model model,HttpServletRequest request)
    {
        model.addAttribute("action",dao.findAll(Action.class));
        model.addAttribute("personnage",dao.findAll(Personnage.class));
        model.addAttribute("tache",dao.findQuery(Tache.class,"select * from tache where idscene="+id));
        request.setAttribute("id",id);
        return "Tache";
    }

    @PostMapping("/new-tache")
    private Object newTache(@ModelAttribute Tache tache,HttpServletRequest request)
    {
        Integer scene = Integer.valueOf(request.getParameter("idscene"));
        Scene sc = new Scene();
        sc.setId(scene);
        Integer pers = Integer.valueOf(request.getParameter("idpers"));
        Personnage p = new Personnage();
        p.setId(pers);
        Integer action = Integer.valueOf(request.getParameter("idaction"));
        Action ac = new Action();
        ac.setId(action);
        tache.setScene(sc);
        tache.setPersonnage(p);
        tache.setType(ac);
        dao.create(tache);
        return new RedirectView("liste-tache?id="+scene);
    }

    //Planning
    @GetMapping("/planning")
    private String planning(@RequestParam int id,Model model,HttpServletRequest request)
    {
        Film t = dao.findById(Film.class,id);
        model.addAttribute("film",t);
        model.addAttribute("scene",dao.findQuery(Scene.class,"select * from vscene where idfilm="+id+" order by debut,idplateau asc "));
        request.setAttribute("id",id);
        return "Planning";
    }

    @GetMapping("/plan")
    private String plan(@RequestParam String debut,@RequestParam String fin, @RequestParam int id,Model model,HttpServletRequest request)
    {
        Film t = dao.findById(Film.class,id);
        model.addAttribute("film",t);
        model.addAttribute("scene",t.planning(debut,fin,dao,request.getParameterValues("scene")));
        request.setAttribute("id",id);
        return "RepPlaning";
    }
}