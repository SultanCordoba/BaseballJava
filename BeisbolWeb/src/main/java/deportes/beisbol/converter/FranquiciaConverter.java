package deportes.beisbol.converter;

import java.time.ZoneId;
import java.util.Iterator;
import java.util.LinkedHashSet;

import deportes.beisbol.jpa.model.Franquicia;
import deportes.beisbol.jpa.model.FranquiciaHistorico;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.RangoFechaBeisbol;

public class FranquiciaConverter {
	public static FranquiciaBeisbol convierteDeBase(Franquicia franquicia) {
		FranquiciaBeisbol resultado = new FranquiciaBeisbol();
		
		resultado.setId(franquicia.getId());
		resultado.setNombre(franquicia.getNombreTablasEs());
		
		Iterator<FranquiciaHistorico> iteraFranqHist = franquicia.getFranquiciaHistoricos().iterator();
		
		String nombreActual = "XXXX";
		FranquiciaHistorico franqHistPaso;
		LinkedHashSet<RangoFechaBeisbol> listaNombres = new LinkedHashSet<>();
		RangoFechaBeisbol rangoNombre = null;
		
		while (iteraFranqHist.hasNext()) {
			franqHistPaso = iteraFranqHist.next();
			
			if (!nombreActual.equalsIgnoreCase(franqHistPaso.getNombreCompletoEs())) {
				nombreActual = franqHistPaso.getNombreCompletoEs();
				
				if (rangoNombre != null) {
					rangoNombre.creaRangoString();
					listaNombres.add(rangoNombre);
				}

				rangoNombre = new RangoFechaBeisbol();
				
				rangoNombre.setFechaInicio(franqHistPaso.getFechaInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				rangoNombre.setNombre(franqHistPaso.getNombreCompletoEs());				
			} 
			rangoNombre.setFechaFin(franqHistPaso.getFechaFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		}
		rangoNombre.creaRangoString();
		listaNombres.add(rangoNombre);
		resultado.setNombres(listaNombres);
		
		resultado.setPais(franquicia.getClub().getPai().getNombreEs());
		
		return resultado;
	}

}
