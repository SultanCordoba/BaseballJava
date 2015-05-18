package deportes.beisbol.converter;

import java.time.ZoneId;
import java.util.Optional;

import deportes.beisbol.jpa.model.Partido;
import deportes.beisbol.jpa.model.PartidoEquipo;
import deportes.beisbol.model.ContrincanteBeisbol;
import deportes.beisbol.model.PartidoBeisbol;

public class PartidoConverter {
	public static PartidoBeisbol convierteDeBase(Partido partido, Optional<String> idioma) {
		PartidoBeisbol resultado = new PartidoBeisbol();
		
		resultado.setComentario(partido.getComentario());
		resultado.setFechaRealizacion(partido.getFechaRealizacion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		
		ContrincanteBeisbol contLocal = new ContrincanteBeisbol();
		ContrincanteBeisbol contVisita = new ContrincanteBeisbol();
		
		for (PartidoEquipo partidoEquipos : partido.getPartidoEquipos()) {
			if (partidoEquipos.getLocalia().equalsIgnoreCase("L")) {
				contLocal.setScore(partidoEquipos.getCarreras());
				contLocal.setEquipo(EquipoConverter.convierteDeBase(partidoEquipos.getEquipo(), idioma));
			}
			else {
				contVisita.setScore(partidoEquipos.getCarreras());
				contVisita.setEquipo(EquipoConverter.convierteDeBase(partidoEquipos.getEquipo(), idioma));
			}
		}
		
		resultado.setLocal(contLocal);
		resultado.setVisita(contVisita);
		
		return resultado;
	}
}
