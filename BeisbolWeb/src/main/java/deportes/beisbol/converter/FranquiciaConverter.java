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
import deportes.beisbol.utils.FechaUtils;
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
		String parqueActual = inicioString;
		FranquiciaHistorico franqHistPaso;
		
		LinkedHashSet<RangoFechaBeisbol> listaNombres = new LinkedHashSet<>();
		LinkedHashSet<RangoFechaBeisbol> listaEscudos = new LinkedHashSet<>();
		LinkedHashSet<RangoFechaBeisbol> listaParques = new LinkedHashSet<>();
		RangoFechaBeisbol rangoNombre = null;
		RangoFechaBeisbol rangoEscudo = null;
		RangoFechaBeisbol rangoParque = null;
		
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
				
				rangoNombre.setFechaInicio(FechaUtils.convertidor(franqHistPaso.getFechaInicio()));
				rangoNombre.setNombre(franqHistPaso.getNombreCompletoEs());				
			} 
			rangoNombre.setFechaFin(FechaUtils.convertidor(franqHistPaso.getFechaFin()));
			
			if (!escudoActual.equalsIgnoreCase(franqHistPaso.getArchivoEscudo())) {
				escudoActual = Strings.nullToEmpty(franqHistPaso.getArchivoEscudo());
				
				if (rangoEscudo != null) {
					if (!Strings.isNullOrEmpty(rangoEscudo.getNombre())) {
						rangoEscudo.creaRangoString();
						listaEscudos.add(rangoEscudo);
					}
				}
				
				rangoEscudo = new RangoFechaBeisbol();
				
				
				
				rangoEscudo.setFechaInicio(FechaUtils.convertidor(franqHistPaso.getFechaInicio()));
				rangoEscudo.setNombre(franqHistPaso.getArchivoEscudo());
			}
			rangoEscudo.setFechaFin(FechaUtils.convertidor(franqHistPaso.getFechaFin()));
			
			if (!parqueActual.equalsIgnoreCase(franqHistPaso.getParque().getNombre())) {
				parqueActual = Strings.nullToEmpty(franqHistPaso.getParque().getNombre());
				
				if (rangoParque != null) {
					if (!Strings.isNullOrEmpty(rangoParque.getNombre())) {
						rangoParque.creaRangoString();
						listaParques.add(rangoParque);
					}
				}
				
				rangoParque = new RangoFechaBeisbol();
				
				rangoParque.setFechaInicio(FechaUtils.convertidor(franqHistPaso.getFechaInicio()));
				rangoParque.setNombre(franqHistPaso.getParque().getNombre());
			}
			rangoParque.setFechaFin(FechaUtils.convertidor(franqHistPaso.getFechaFin()));
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
		
		//logger.info(rangoParque.getFechaInicio().toString());
		// logger.info(rangoParque.getFechaFin().toString());
		
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
