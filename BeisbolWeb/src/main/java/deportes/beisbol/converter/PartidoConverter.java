package deportes.beisbol.converter;

import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import deportes.beisbol.jpa.model.Partido;
import deportes.beisbol.jpa.model.PartidoEquipo;
import deportes.beisbol.model.ContrincanteBeisbol;
import deportes.beisbol.model.PartidoBeisbol;

public class PartidoConverter {
	public static PartidoBeisbol convierteDeBase(Partido partido, Optional<String> idioma) {
		PartidoBeisbol resultado = new PartidoBeisbol();
		
		resultado.setComentario(partido.getComentario());
		
		// La conversión de Date, es porque el objeto partido trae un tipo java.sql.Date en 
		// lugar de java.util.Date
		Date fecha = new Date(partido.getFechaRealizacion().getTime());
		resultado.setFechaRealizacion(fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		
		ContrincanteBeisbol contLocal = new ContrincanteBeisbol();
		ContrincanteBeisbol contVisita = new ContrincanteBeisbol();
		
		for (PartidoEquipo partidoEquipos : partido.getPartidoEquipos()) {
			if (partidoEquipos.getLocalia().equalsIgnoreCase("L")) {
				contLocal.setScore(partidoEquipos.getCarreras());
				contLocal.setEquipo(EquipoConverter.convierteDeBase(partidoEquipos.getEquipo(), idioma.get()));
			}
			else {
				contVisita.setScore(partidoEquipos.getCarreras());
				contVisita.setEquipo(EquipoConverter.convierteDeBase(partidoEquipos.getEquipo(), idioma.get()));
			}
		}
		
		resultado.setLocal(contLocal);
		resultado.setVisita(contVisita);
		
		return resultado;
	}
}
