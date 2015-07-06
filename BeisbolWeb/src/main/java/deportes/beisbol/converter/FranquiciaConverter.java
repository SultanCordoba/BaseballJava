package deportes.beisbol.converter;

import java.util.Iterator;
import java.util.LinkedHashSet;

/* import org.slf4j.Logger;
import org.slf4j.LoggerFactory; */

import com.google.common.base.Strings;

import deportes.beisbol.jpa.model.Franquicia;
import deportes.beisbol.jpa.model.FranquiciaHistorico;
import deportes.beisbol.jpa.model.FranquiciaHistoricoInt;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.RangoFechaBeisbol;
import deportes.beisbol.utils.FormatoUtils;

public class FranquiciaConverter {
	
	//private static final Logger logger = LoggerFactory.getLogger(FranquiciaConverter.class);
	
	public static String nombreCompletoIdioma(FranquiciaHistorico franquiciaHistorico, String idioma) {
		String resultado = franquiciaHistorico.getNombreCompletoEs();
		
		String idiomaPaso = Strings.nullToEmpty(idioma);
		
		if (!idiomaPaso.equalsIgnoreCase("ES")) {
			Iterator<FranquiciaHistoricoInt> iteraFranqHist = franquiciaHistorico.getFranquiciaHistoricoInts().iterator();
			FranquiciaHistoricoInt franqPaso;
			
			while (iteraFranqHist.hasNext()) {
				franqPaso = iteraFranqHist.next();
				
				if (franqPaso.getIdioma().getAbreviatura().equalsIgnoreCase(idiomaPaso)) {
					resultado = franqPaso.getNombreCompleto();
				}
			}
		}
		
		return resultado;
	}
	
	public static FranquiciaBeisbol convierteDeBase(Franquicia franquicia, String idioma) {
		
		String inicioString = "XXXX";
		
		FranquiciaBeisbol resultado = new FranquiciaBeisbol();
		
		resultado.setId(franquicia.getId());
		resultado.setNombre(franquicia.getNombreTablasEs());
		
		Iterator<FranquiciaHistorico> iteraFranqHist = franquicia.getFranquiciaHistoricos().iterator();
		
		String nombreActual = inicioString;
		String escudoActual = inicioString;
		String parqueActual = inicioString;
		FranquiciaHistorico franqHistPaso;
		
		LinkedHashSet<RangoFechaBeisbol> listaNombres = new LinkedHashSet<>();
		LinkedHashSet<RangoFechaBeisbol> listaEscudos = new LinkedHashSet<>();
		LinkedHashSet<RangoFechaBeisbol> listaParques = new LinkedHashSet<>();
		RangoFechaBeisbol rangoNombre = null;
		RangoFechaBeisbol rangoEscudo = null;
		RangoFechaBeisbol rangoParque = null;
		
		String nombreCompara;
		
		while (iteraFranqHist.hasNext()) {
			franqHistPaso = iteraFranqHist.next();
			nombreCompara = nombreCompletoIdioma(franqHistPaso, idioma);
			
			if (!nombreActual.equalsIgnoreCase(nombreCompara)) {
				nombreActual = nombreCompara;
				
				if (rangoNombre != null) {
					if (!Strings.isNullOrEmpty(rangoNombre.getNombre())) {
						rangoNombre.creaRangoString();
						listaNombres.add(rangoNombre);
					}
				}
				rangoNombre = new RangoFechaBeisbol();
				rangoNombre.setFechaInicio(FormatoUtils.convertidorFecha(franqHistPaso.getFechaInicio()));
				rangoNombre.setNombre(nombreActual);				
			} 
			rangoNombre.setFechaFin(FormatoUtils.convertidorFecha(franqHistPaso.getFechaFin()));
			
			if (!escudoActual.equalsIgnoreCase(franqHistPaso.getArchivoEscudo())) {
				escudoActual = Strings.nullToEmpty(franqHistPaso.getArchivoEscudo());
				
				if (rangoEscudo != null) {
					if (!Strings.isNullOrEmpty(rangoEscudo.getNombre())) {
						rangoEscudo.creaRangoString();
						listaEscudos.add(rangoEscudo);
					}
				}
				rangoEscudo = new RangoFechaBeisbol();
				rangoEscudo.setFechaInicio(FormatoUtils.convertidorFecha(franqHistPaso.getFechaInicio()));
				rangoEscudo.setNombre(franqHistPaso.getArchivoEscudo());
			}
			rangoEscudo.setFechaFin(FormatoUtils.convertidorFecha(franqHistPaso.getFechaFin()));
			
			if (!parqueActual.equalsIgnoreCase(franqHistPaso.getParque().getNombre())) {
				parqueActual = Strings.nullToEmpty(franqHistPaso.getParque().getNombre());
				
				if (rangoParque != null) {
					if (!Strings.isNullOrEmpty(rangoParque.getNombre())) {
						rangoParque.creaRangoString();
						listaParques.add(rangoParque);
					}
				}
				rangoParque = new RangoFechaBeisbol();
				rangoParque.setFechaInicio(FormatoUtils.convertidorFecha(franqHistPaso.getFechaInicio()));
				rangoParque.setNombre(franqHistPaso.getParque().getNombre());
			}
			rangoParque.setFechaFin(FormatoUtils.convertidorFecha(franqHistPaso.getFechaFin()));
		}
		
		if (!nombreActual.equals(inicioString)) {
			if (!Strings.isNullOrEmpty(rangoNombre.getNombre())) {
				rangoNombre.creaRangoString();
				listaNombres.add(rangoNombre);
			}
		}
		resultado.setNombres(listaNombres);

		if (!escudoActual.equals(inicioString)) {
			if (!Strings.isNullOrEmpty(rangoEscudo.getNombre())) {
				rangoEscudo.creaRangoString();
				listaEscudos.add(rangoEscudo);
			}
		}		
		resultado.setEscudos(listaEscudos);
		
		if (!parqueActual.equals(inicioString)) {
			if (!Strings.isNullOrEmpty(rangoParque.getNombre())) {
				rangoParque.creaRangoString();
				listaParques.add(rangoParque);
			}
		}
		resultado.setParques(listaParques);
		resultado.setPais(franquicia.getClub().getPai().getNombreEs());
		
		return resultado;
	}
}
