package deportes.beisbol.utils;

import java.util.ArrayList;
import java.util.TreeMap;

/* import org.slf4j.Logger;
import org.slf4j.LoggerFactory; */

import com.google.common.base.Joiner;

import deportes.beisbol.converter.ParticipanteConverter;
import deportes.beisbol.jpa.model.LigaHistorico;
import deportes.beisbol.jpa.model.Participante;

public class ConstructorBreadcrumb {
	
	//private static final Logger logger = LoggerFactory.getLogger(ConstructorBreadcrumb.class);
	
	public static TreeMap<String, String> construyeInicio() {
		TreeMap<String, String> resultado = new TreeMap<>();
		
		resultado.put("/inicio", "header.inicio");
		
		return resultado;
	}
	
	public static TreeMap<String, String> construyeLigasAll() {
		TreeMap<String, String> resultado = construyeInicio();
		
		resultado.put("/liga/showall", "header.ligas");
		
		return resultado;
	}
	
	public static TreeMap<String, String> construyeLiga(LigaHistorico ligaBeisbol, String zona) {
		TreeMap<String, String> resultado = construyeLigasAll();
		
		Joiner joiner = Joiner.on("/").skipNulls();
		ArrayList<String> urlLiga = new ArrayList<>();
		urlLiga.add("X");
		urlLiga.add("liga");
		urlLiga.add(String.valueOf(ligaBeisbol.getLiga().getId()));
		urlLiga.add("show");
		urlLiga.add(zona);
		
		resultado.put(joiner.join(urlLiga).toString(), ligaBeisbol.getSiglas());
		
		return resultado;
	}	
	
	public static TreeMap<String, String> construyeEquipo(Participante participante, String zona) {
		TreeMap<String, String> resultado = construyeLiga(participante.getTemporada().getLigaHistorico(), zona);
		
		Joiner joiner = Joiner.on("/").skipNulls();
		ArrayList<String> urlLiga = new ArrayList<>();
		
		switch(zona.toUpperCase()) {
		case "EQUIPOS":
			urlLiga.add("X");
			urlLiga.add("franquicia");
			urlLiga.add(String.valueOf(participante.getEquipos().iterator().next().getFranquiciaHistorico().getId()));
			urlLiga.add("show");
						
			resultado.put(joiner.join(urlLiga).toString(), ParticipanteConverter.nombreParticipante(participante, false, null));			
			break;
			
		case "TEMPORADAS":
			urlLiga.add("X");
			urlLiga.add("temporada");
			urlLiga.add(String.valueOf(participante.getTemporada().getId()));
			urlLiga.add("show");
						
			resultado.put(joiner.join(urlLiga).toString(), participante.getTemporada().getNombre());
			break;
		}
				
		return resultado;
	}
}
