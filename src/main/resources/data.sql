drop table IF exists TBL_MOVIES;

CREATE TABLE TBL_MOVIES (
   id IDENTITY PRIMARY KEY NOT NULL,
   title VARCHAR(50) NOT NULL,
   director VARCHAR(50) NOT NULL,
   rating INTEGER(50) NOT NULL
);

insert into TBL_MOVIES(id,title,director,rating) values (1,'Pelicula 1','Director 1',3);
insert into TBL_MOVIES(id,title,director,rating) values (2,'Pelicula 2','Director 2',1);
insert into TBL_MOVIES(id,title,director,rating) values (3,'Pelicula 3','Director 3',4);
insert into TBL_MOVIES(id,title,director,rating) values (4,'Pelicula 4','Director 4',5);
insert into TBL_MOVIES(id,title,director,rating) values (5,'Pelicula 5','Director 5',3);
insert into TBL_MOVIES(id,title,director,rating) values (6,'Pelicula 6','Director 6',2);
insert into TBL_MOVIES(id,title,director,rating) values (7,'Pelicula 7','Director 7',1);