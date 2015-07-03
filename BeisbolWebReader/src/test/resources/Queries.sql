select * from baseball.temporada
where liga_id = 1;

select * from baseball.etapa
where temporada_id = 197;

select r.nombre_grupo, eq.nombre_tablas_es, sum(pe.gano), sum(pe.perdio)
from baseball.partido_equipo pe inner join baseball.partido p
  on pe.partido_id = p.id
  inner join baseball.equipo eq on pe.equipo_id = eq.id
  inner join baseball.record r on eq.participante_id = r.participante_id
where p.etapa_id = 522 and r.etapa_id = p.etapa_id
group by r.nombre_grupo, eq.nombre_tablas_es
order by 1, 3 desc;

select temporada_nombre, fecha_realizacion, juego_dia, equipo_visita,
  carr_visita, equipo_local, carr_local
from baseball.partido_vista
where siglas_liga = 'LMB' and temporada_nombre = '2015'
and equipo_local in ('TIJ', 'AGS') and equipo_visita in ('TIJ', 'AGS');

select fecha_realizacion, count(*)
from baseball.partido
where fecha_realizacion > '2015-05-01' and etapa_id = 522
group by fecha_realizacion
order by 1 desc;

select record_id, nombre_grupo, nombre_tablas_es, ganados, perdidos, pctje 
from baseball.records_vista
where siglas_liga = 'LMB' and temporada_nombre = '2015'
order by 2, 6 desc;

update baseball.record
set ganados = ganados-1
where id in (3600, 3614, 3605, 3612, 3606, 3613, 3599);

select partido_id, juego_dia, equipo_visita, carr_visita,
  equipo_local, carr_local
from baseball.partido_vista
where fecha_realizacion = '2015-05-16'
order by equipo_visita, juego_dia, partido_id;


select max(fecha_realizacion) from baseball.partido;


ALTER TABLE `baseball`.`partido_equipo` 
AUTO_INCREMENT = 49714 ;

ALTER TABLE `baseball`.`partido` 
AUTO_INCREMENT = 26720 ;

select * from roster_vista
where siglas_es = 'LMB' and temporada_nombre = '2014'
and posicion like '%M%';

select temporada_id, temporada_nombre, etapa_id, nombre_etapa,
  equipo_id, participante_id, nombre_tablas_es, ganados, perdidos, record_id
from records_vista
where siglas_liga = 'LN' and temporada_nombre = '1984'
order by record_id;

select * from baseball.etapa where temporada_id = 68;

select * from baseball.equipo where participante_id = 1144;

select * from baseball.record_int;

insert into baseball.record_int values (null, 3617, 2, 'Semifinal 1', now());
insert into baseball.record_int values (null, 3618, 2, 'Semifinal 1', now());
insert into baseball.record_int values (null, 3619, 2, 'Semifinal 2', now());
insert into baseball.record_int values (null, 3620, 2, 'Semifinal 2', now());
insert into baseball.record_int values (null, 3621, 2, 'Championship Series', now());
insert into baseball.record_int values (null, 3622, 2, 'Championship Series', now());

insert into baseball.record_int values (null, 3623, 2, 'Semifinal 1', now());
insert into baseball.record_int values (null, 3624, 2, 'Semifinal 1', now());
insert into baseball.record_int values (null, 3625, 2, 'Semifinal 2', now());
insert into baseball.record_int values (null, 3626, 2, 'Semifinal 2', now());
insert into baseball.record_int values (null, 3627, 2, 'Championship Series', now());
insert into baseball.record_int values (null, 3628, 2, 'Championship Series', now());

insert into baseball.record_int values (null, 3629, 2, 'Semifinal 1', now());
insert into baseball.record_int values (null, 3630, 2, 'Semifinal 1', now());
insert into baseball.record_int values (null, 3631, 2, 'Semifinal 2', now());
insert into baseball.record_int values (null, 3632, 2, 'Semifinal 2', now());
insert into baseball.record_int values (null, 3633, 2, 'Championship Series', now());
insert into baseball.record_int values (null, 3634, 2, 'Championship Series', now());

insert into baseball.record_int values (null, 3635, 2, 'Semifinal 1', now());
insert into baseball.record_int values (null, 3636, 2, 'Semifinal 1', now());
insert into baseball.record_int values (null, 3637, 2, 'Semifinal 2', now());
insert into baseball.record_int values (null, 3638, 2, 'Semifinal 2', now());
insert into baseball.record_int values (null, 3639, 2, 'Championship Series', now());
insert into baseball.record_int values (null, 3640, 2, 'Championship Series', now());



