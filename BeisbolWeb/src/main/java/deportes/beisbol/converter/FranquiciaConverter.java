package deportes.beisbol.converter;

import java.time.ZoneId;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

import deportes.beisbol.jpa.model.Franquicia;
import deportes.beisbol.jpa.model.FranquiciaHistorico;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.RangoFechaBeisbol;
import deportes.beisbol.web.controller.LigaController;

public class FranquiciaConverter {
	
	private static final Logger logger = LoggerFactory.getLogger(FranquiciaConverter.class);
	
	public static FranquiciaBeisbol convierteDeBase(Franquicia franquicia) {
		
		String inicioString = "XXXX";
		
		FranquiciaBeisbol resultado = new FranquiciaBeisbol();
		
		resultado.setId(franquicia.getId());
		resultado.setNombre(franquicia.getNombreTablasEs());
		
		Iterator<FranquiciaHistorico> iteraFranqHist = franquicia.getFranquiciaHistoricos().iterator();
		
		String nombreActual = inicioString;
		String escudoActual = inicioString;
		FranquiciaHistorico franqHistPaso;
		
		LinkedHashSet<RangoFechaBeisbol> listaNombres = new LinkedHashSet<>();
		LinkedHashSet<RangoFechaBeisbol> listaEscudos = new LinkedHashSet<>();
		RangoFechaBeisbol rangoNombre = null;
		RangoFechaBeisbol rangoEscudo = null;
		
		logger.info("Procesando " + franquicia.getNombreTablasEs() + " con id " + franquicia.getId());
		
		while (iteraFranqHist.hasNext()) {
			franqHistPaso = iteraFranqHist.next();
			
			if (!nombreActual.equalsIgnoreCase(franqHistPaso.getNombreCompletoEs())) {
				nombreActual = franqHistPaso.getNombreCompletoEs();
				
				if (rangoNombre != null) {
					if (!Strings.isNullOrEmpty(rangoNombre.getNombre())) {
						rangoNombre.creaRangoString();
						listaNombres.add(rangoNombre);
					}
				}

				rangoNombre = new RangoFechaBeisbol();
				
				rangoNombre.setFechaInicio(franqHistPaso.getFechaInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				rangoNombre.setNombre(franqHistPaso.getNombreCompletoEs());				
			} 
			rangoNombre.setFechaFin(franqHistPaso.getFechaFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			
			if (!escudoActual.equalsIgnoreCase(franqHistPaso.getArchivoEscudo())) {
				escudoActual = Strings.nullToEmpty(franqHistPaso.getArchivoEscudo());
				
				if (rangoEscudo != null) {
					if (!Strings.isNullOrEmpty(rangoEscudo.getNombre())) {
						rangoEscudo.creaRangoString();
						listaEscudos.add(rangoEscudo);
					}
				}
				
				rangoEscudo = new RangoFechaBeisbol();
				
				rangoEscudo.setFechaInicio(franqHistPaso.getFechaInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				rangoEscudo.setNombre(franqHistPaso.getArchivoEscudo());
			}
			rangoEscudo.setFechaFin(franqHistPaso.getFechaFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			
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
		
		resultado.setPais(franquicia.getClub().getPai().getNombreEs());
		
		return resultado;
	}

}
