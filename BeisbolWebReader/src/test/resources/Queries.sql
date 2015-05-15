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

select record_id, nombre_tablas_es, ganados, perdidos
from baseball.records_vista
where siglas_liga = 'LMB' and temporada_nombre = '2015';

update baseball.record
set ganados = perdidos - 1, ganados = ganados
where id in (3608, 3613, 3614, 3605);

select partido_id, juego_dia, equipo_visita, carr_visita,
  equipo_local, carr_local
from baseball.partido_vista
where fecha_realizacion = '2015-05-14'
order by equipo_visita, juego_dia, partido_id;

select * from baseball.franquicia
where id = 23;

select max(id) from baseball.partido_equipo;

select * from baseball.records_vista
where temporada_nombre = '2015' and liga_id = 1
order by nombre_grupo, pctje desc;

ALTER TABLE `baseball`.`partido_equipo` 
AUTO_INCREMENT = 49714 ;

ALTER TABLE `baseball`.`partido` 
AUTO_INCREMENT = 26720 ;

