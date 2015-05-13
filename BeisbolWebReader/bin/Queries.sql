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

select max(id) from baseball.partido;

select * from baseball.records_vista
where temporada_nombre = '2015' and liga_id = 1
order by nombre_grupo, pctje desc;

ALTER TABLE `baseball`.`partido_equipo` 
AUTO_INCREMENT = 49578 ;

ALTER TABLE `baseball`.`partido` 
AUTO_INCREMENT = 26652 ;