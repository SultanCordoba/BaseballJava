select p.* 
from beisbol.partido p inner join beisbol.etapa e on p.etapa_id = e.id
       inner join beisbol.temporada t on e.temporada_id = t.id 
where t.liga_id = 1 and juego_dia = 2 and p.fecha_realizacion > '2013-03-01'
order by p.fecha_realizacion