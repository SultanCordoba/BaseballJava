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
				
				nombreEquipo = FranquiciaConverter.nombreCompletoIdioma(equipo.getFranquiciaHistorico(), idioma);
				
				/* nombreEquipo = equipo.getNombreCompletoEs();
				
				if (idioma.isPresent()) {
					String idiomaPaso = idioma.get().toUpperCase();
					
					nombreEquipo = FranquiciaConverter.nombreCompletoIdioma(equipo.getFranquiciaHistorico(), idioma);
					
					 if (!idiomaPaso.equalsIgnoreCase("ES")) {
						Iterator<FranquiciaHistoricoInt> iteraFranqHist = equipo.getFranquiciaHistorico().getFranquiciaHistoricoInts().iterator();
						FranquiciaHistoricoInt franqPaso;
						
						while (iteraFranqHist.hasNext()) {
							franqPaso = iteraFranqHist.next();
							
							if (franqPaso.getIdioma().getAbreviatura().equalsIgnoreCase(idiomaPaso)) {
								nombreEquipo = franqPaso.getNombreCompleto();
								break;
							}
						}
						
					} 
				} */
				
			}
			else {
				nombreEquipo = equipo.getNombreTablasEs();
			}
			
			fullName.add(nombreEquipo);
		}
		
		Joiner joiner = Joiner.on(" / ").skipNulls();
		resultado = joiner.join(fullName);
		
		return resultado;
	}
}
