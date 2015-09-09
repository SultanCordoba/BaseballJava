package deportes.beisbol.converter;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

import deportes.beisbol.jpa.model.Equipo;
import deportes.beisbol.jpa.model.Participante;

public class ParticipanteConverter {
	public static String nombreParticipante(Participante participante, boolean abreviado, String idioma) {
		String resultado;
		LinkedHashSet<String> fullName = new LinkedHashSet<>();
		
		Iterator<Equipo> iteraEquipos = participante.getEquipos().iterator();
		Equipo equipo;
		String nombreEquipo;
		
		while (iteraEquipos.hasNext()) {
			equipo = iteraEquipos.next();
			
			if (!abreviado) {
				nombreEquipo = FranquiciaConverter.nombreCompletoIdioma(equipo.getFranquiciaHistorico(), idioma);				
			}
			else {
				nombreEquipo = equipo.getNombreTablasEs();
			}
			
			fullName.add(nombreEquipo);
		}
		
		/* Joiner joiner = Joiner.on(" / ").skipNulls();
		resultado = joiner.join(fullName); */
		
		resultado = fullName.stream().filter(p -> p != null).collect(Collectors.joining(" / "));
		
		return resultado;
	}
}
