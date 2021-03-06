package deportes.beisbol.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/* import org.slf4j.Logger;
import org.slf4j.LoggerFactory; */

import java.util.Optional;
import java.util.stream.Collectors;

import deportes.beisbol.converter.ParticipanteConverter;
import deportes.beisbol.jpa.model.LigaHistorico;
import deportes.beisbol.jpa.model.Participante;

public class ConstructorBreadcrumb {
	
	//private static final Logger logger = LoggerFactory.getLogger(ConstructorBreadcrumb.class);
	
	public static LinkedHashMap<String, String> construyeInicio() {
		LinkedHashMap<String, String> resultado = new LinkedHashMap<>();
		
		resultado.put("/inicio", "header.inicio");
		
		return resultado;
	}
	
	public static LinkedHashMap<String, String> construyeLigasAll() {
		LinkedHashMap<String, String> resultado = construyeInicio();
		
		resultado.put("/liga/showall", "header.ligas");
		
		return resultado;
	}
	
	public static LinkedHashMap<String, String> construyeLiga(LigaHistorico ligaBeisbol, String zona) {
		LinkedHashMap<String, String> resultado = construyeLigasAll();
		
		// Joiner joiner = Joiner.on("/").skipNulls();
		ArrayList<String> urlLiga = new ArrayList<>();
		urlLiga.add("X");
		urlLiga.add("liga");
		urlLiga.add(String.valueOf(ligaBeisbol.getLiga().getId()));
		urlLiga.add("show");
		urlLiga.add(zona);
		
		String unirBread = urlLiga.stream().filter(p -> p != null).collect(Collectors.joining("/"));
		
		//resultado.put(joiner.join(urlLiga).toString(), ligaBeisbol.getSiglas());
		resultado.put(unirBread, ligaBeisbol.getSiglas());
		
		return resultado;
	}	
	
	public static LinkedHashMap<String, String> construyeEquipo(Participante participante, String zona, Optional<String> idioma) {
		LinkedHashMap<String, String> resultado = construyeLiga(participante.getTemporada().getLigaHistorico(), zona);
		
		//Joiner joiner = Joiner.on("/").skipNulls();
		ArrayList<String> urlLiga = new ArrayList<>();
		String unirBread;
		
		switch(zona.toUpperCase()) {
		case "E":
			urlLiga.add("X");
			urlLiga.add("franquicia");
			urlLiga.add(String.valueOf(participante.getEquipos().iterator().next().getFranquiciaHistorico().getFranquicia().getId()));
			urlLiga.add("show");
						
			unirBread = urlLiga.stream().filter(p -> p != null).collect(Collectors.joining("/"));
			
			// resultado.put(joiner.join(urlLiga).toString(), ParticipanteConverter.nombreParticipante(participante, false, idioma.get()));			
			resultado.put(unirBread, ParticipanteConverter.nombreParticipante(participante, false, idioma.get()));
			break;
			
		case "T":
			urlLiga.add("X");
			urlLiga.add("temporada");
			urlLiga.add(String.valueOf(participante.getTemporada().getId()));
			urlLiga.add("show");
						
			unirBread = urlLiga.stream().filter(p -> p != null).collect(Collectors.joining("/"));
			
			//resultado.put(joiner.join(urlLiga).toString(), participante.getTemporada().getNombre());
			resultado.put(unirBread, participante.getTemporada().getNombre());
			break;
		}
				
		return resultado;
	}
	
	public static LinkedHashMap<String, String> construyeJugador() {
		LinkedHashMap<String, String> resultado = construyeInicio();
		
		resultado.put("/jugador/search", "header.jugadores");
		
		return resultado;
	}
}
