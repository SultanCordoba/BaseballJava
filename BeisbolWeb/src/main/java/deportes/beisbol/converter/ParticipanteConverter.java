package deportes.beisbol.converter;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

import deportes.beisbol.jpa.model.Participante;

public class ParticipanteConverter {
	public static String nombreParticipante(Participante participante, boolean abreviado, String idioma) {
		String resultado;
		LinkedHashSet<String> fullName = new LinkedHashSet<>();
		
		participante.getEquipos().forEach(
		  (equipo) -> {
			  String nombreEquipo;
				if (!abreviado) {
					nombreEquipo = FranquiciaConverter.nombreCompletoIdioma
							(equipo.getFranquiciaHistorico(), idioma);				
				}
				else {
					nombreEquipo = equipo.getNombreTablasEs();
				}
				fullName.add(nombreEquipo);
		  }
		);

		resultado = fullName.stream().filter(p -> p != null)
				.collect(Collectors.joining(" / "));
		
		return resultado;
	}
}
