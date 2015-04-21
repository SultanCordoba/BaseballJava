package deportes.beisbol.converter;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;

import com.google.common.base.Joiner;

import deportes.beisbol.jpa.model.Equipo;
import deportes.beisbol.jpa.model.Participante;

public class ParticipanteConverter {
	public static String nombreParticipante(Participante participante, boolean abreviado, Optional<String> idioma) {
		String resultado;
		LinkedHashSet<String> fullName = new LinkedHashSet<>();
		
		Iterator<Equipo> iteraEquipos = participante.getEquipos().iterator();
		Equipo equipo;
		String nombreEquipo;
		
		while (iteraEquipos.hasNext()) {
			equipo = iteraEquipos.next();
			
			if (!abreviado) {
				nombreEquipo = equipo.getNombreCompletoEs();
			}
			else {
				nombreEquipo = equipo.getNombreTablasEs();
			}
			
			// Calcular nombre en otro idioma si se especifica
			
			fullName.add(nombreEquipo);
		}
		
		Joiner joiner = Joiner.on(" / ").skipNulls();
		resultado = joiner.join(fullName);
		
		return resultado;
	}
}
