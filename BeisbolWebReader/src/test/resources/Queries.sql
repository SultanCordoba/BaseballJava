select * from baseball.temporada;

select * from baseball.etapa
where temporada_id = 202;

select r.nombre_grupo, eq.nombre_tablas_es, sum(pe.gano), sum(pe.perdio)
from baseball.partido_equipo pe inner join baseball.partido p
  on pe.partido_id = p.id
  inner join baseball.equipo eq on pe.equipo_id = eq.id
  inner join baseball.record r on eq.participante_id = r.participante_id
where p.etapa_id = 522 and r.etapa_id = p.etapa_id
group by r.nombre_grupo, eq.nombre_tablas_es
order by 1, 3 desc;

select max(fecha_realizacion)
from baseball.partido;


select fecha_realizacion, count(*)
from baseball.partido
where fecha_realizacion > '2015-07-11' and etapa_id = 522
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

select * from baseball.etapa where temporada_id = 202;

select * from baseball.records_vista where temporada_id = 202;

update baseball.record
set perdidos = 2
where id in (3654, 3657);

update baseball.record
set ganados = 2
where id in (3655, 3656);


insert into baseball.record values
(null, 525, 1, 1875, 'Serie PlayOff 5', 0, 0, 0, now()); 

insert into baseball.record values
(null, 525, 1, 1882, 'Serie PlayOff 5', 0, 0, 0, now()); 

insert into baseball.record values
(null, 525, 1, 1879, 'Serie PlayOff 6', 0, 0, 0, now()); 

insert into baseball.record values
(null, 525, 1, 1878, 'Serie PlayOff 6', 0, 0, 0, now());

select max(id) from baseball.record;

select * from baseball.record where id > 3640;

delete from baseball.record
where id = 3645;

update baseball.record
set participante_id = 1885, ganados = 1
where id = 3644;

select * from baseball.partido
where fecha_realizacion > '2015-08-28'
order by id;

select * from franquicia_historico_int order by id desc;

insert into franquicia values (null, 1, 12, 166, 'CAV', 'Tigres de Ciego de Ávila', 'CIEGO DE ÁVILA', 'CiegoAvila2016.png', 1, null, null, now());

insert into franquicia_historico values (null, 242, 1, '2016-02-01', '2100-12-31', 'CAV', 'Tigres de Ciego de Ávila', 'CIEGO DE ÁVILA', 
  'CiegoAvila2016.png', 1, 'Ciego de Avila Tigers', null, now());
  
insert into franquicia_historico_int values (null, 440, 1, 'Ciego de Avila Tigers', now());
  
  

