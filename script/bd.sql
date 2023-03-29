create role film login password 'film';
create database film;
alter database film owner to film;


create table film(
                     id serial primary key ,
                     film varchar(255)
);
insert into film(film) values ('Shark 2');
insert into film(film) values ('Adala 2');
select * from film;

create table plateau(
                        idplateau serial primary key ,
                        nom_plateau varchar(50),
                        x decimal(20,4),
                        y decimal(20,4)
);
insert into plateau(nom_plateau,x,y) values ('Andranomena',10,20),('Antanimena',5,15),('Analakely',6,16),('Ambohibao',11,21),('Ankadifotsy',4,14);
select * from plateau;

create table action(
                       idaction serial primary key ,
                       designation varchar(50)
);
insert into action(designation) values ('action'),('dialogue');

create table personnage(
                           idpers serial primary key ,
                           nom varchar(80)
);
insert into personnage(nom) values ('Iaritina kely'),
                                   ('Booba be'),
                                   ('Jason Statham'),
                                   ('Dwayne Jhonson');
select * from personnage;

create table scene(
                      idscene serial primary key,
                      nom_scene varchar(80) unique ,
                      idfilm int references film (id),
                      idplateau int references plateau (idplateau)
);

insert into scene(idfilm, nom_scene,idplateau) VALUES (1,'Scene milomano',1),(1,'Scene maka sary anaty rano',2);
select * from scene;

create table tache(
                      numero serial primary key ,
                      idscene int references scene(idscene),
                      idpers int references personnage(idpers),
                      idaction int references action (idaction),
                      dialogue text,
                      duree time
);
insert into tache (idscene, idpers, idaction, dialogue, duree) VALUES (1,1,1,'rigole','00:00:05'),
                                                                      (1,2,2,'je t aime','00:00:03');
select * from tache;


create table admin(
                      idadmin serial primary key ,
                      username varchar(50),
                      mdp varchar(50)
);

insert into admin (username, mdp) VALUES ('admin','admin');


alter table scene add column debut time;
alter table scene add column fin time;


select * from scene;
select * from plateau;

create table Disponibilite(
                              id serial primary key,
                              idscene int,
                              idplateau int ,
                              dates date not null,
                              foreign key (idscene) references scene(idscene),
                              foreign key (idplateau) references plateau(idplateau)
);

create or replace function getDurree(idsce integer)
    returns time
    language plpgsql
as
$$
Declare
somme time :='00:00:00';
Begin
select SUM(duree) into somme from tache where idscene=idsce;
if somme = null then
        somme:= '00:00:00';
end if;
return somme;
End;
$$;

create or replace view vScene as
select idscene,nom_scene,idfilm,idplateau,debut, COALESCE(getDurree(idscene),'00:00:00') as fin from scene where idscene not in (select idscene from Disponibilite);

select * from vScene;
select * from vscene where idfilm=1 order by debut,idplateau asc;




