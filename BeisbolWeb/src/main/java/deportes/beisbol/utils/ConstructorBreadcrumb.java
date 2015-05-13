package deportes.beisbol.utils;

import java.util.ArrayList;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;

import deportes.beisbol.jpa.model.LigaHistorico;
import deportes.beisbol.model.LigaBeisbol;

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
	
	public static TreeMap<String, String> construyeLiga(LigaHistorico ligaBeisbol) {
		TreeMap<String, String> resultado = construyeLigasAll();
		
		Joiner joiner = Joiner.on("/").skipNulls();
		ArrayList<String> urlLiga = new ArrayList<>();
		urlLiga.add("X");
		urlLiga.add("liga");
		urlLiga.add(String.valueOf(ligaBeisbol.getLiga().getId()));
		urlLiga.add("show");
		urlLiga.add("temporadas");
		
		resultado.put(joiner.join(urlLiga).toString(), ligaBeisbol.getSiglas());
		
		return resultado;
	}
	
	public static TreeMap<String, String> construyeFranquicia(LigaHistorico ligaBeisbol) {
		
		TreeMap<String, String> resultado = construyeLigasAll();
		
		Joiner joiner = Joiner.on("/").skipNulls();
		ArrayList<String> urlLiga = new ArrayList<>();
		urlLiga.add("X");
		urlLiga.add("liga");
		urlLiga.add(String.valueOf(ligaBeisbol.getLiga().getId()));
		urlLiga.add("show");
		urlLiga.add("equipos");
		
		resultado.put(joiner.join(urlLiga).toString(), ligaBeisbol.getSiglas());
		
		return resultado;
	}
	

}
