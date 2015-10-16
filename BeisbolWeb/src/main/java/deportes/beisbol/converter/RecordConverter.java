package deportes.beisbol.converter;

import java.util.Optional;

import deportes.beisbol.jpa.model.Equipo;
import deportes.beisbol.jpa.model.Record;
import deportes.beisbol.jpa.model.RecordInt;
import deportes.beisbol.model.RecordBeisbol;
import deportes.beisbol.utils.RecordEtapa;

public class RecordConverter {
	public static RecordBeisbol convierteDeBase(Record recordBase, Optional<String> idioma) {
		RecordBeisbol resultado = new RecordBeisbol();
		
		resultado.setNombreGrupo(recordBase.getNombreGrupo());
		
		if (idioma.isPresent()) {
			String idiomaCompara = idioma.get().toUpperCase();
			
			if (!idiomaCompara.equalsIgnoreCase("ES")) {
				Optional<RecordInt> recordIntPaso = recordBase.getRecordInts().stream()
				  .filter(ri -> ri.getIdioma().getAbreviatura().equalsIgnoreCase(idiomaCompara))
				  .findFirst();
				
				if (recordIntPaso.isPresent()) {
					resultado.setNombreGrupo(recordIntPaso.get().getNombreGrupo());
				}				
			}
		}
		
		resultado.setGanados(recordBase.getGanados());
		resultado.setPerdidos(recordBase.getPerdidos());
		resultado.setPorcentaje((recordBase.getGanados() + recordBase.getPerdidos()) > 0 ? 
				recordBase.getGanados() / ((double) recordBase.getGanados() + recordBase.getPerdidos()) : 0);
		resultado.setIdVuelta(recordBase.getVuelta().getId());
		resultado.setNombreVuelta(recordBase.getVuelta().getNombre());
		
		return resultado;
	}
	
	public static RecordEtapa convierteAuxDeBase(Record recordBase) {
		RecordEtapa resultado = new RecordEtapa();
		
		resultado.setTemporadaNombre(recordBase.getParticipante().getTemporada().getNombre());
		String etapaNombre = recordBase.getEtapa().getNombre();
		
		resultado.setEtapaNombre(etapaNombre);
		resultado.setGanados(recordBase.getGanados());
		resultado.setPerdidos(recordBase.getPerdidos());
		resultado.setParticipanteId(recordBase.getParticipante().getId());
		
		Optional<Equipo> equipoPaso = recordBase.getParticipante().getEquipos()
		.stream().filter(e -> e.getCampeon() == 1).findFirst();
		
		resultado.setCampeon(equipoPaso.isPresent());
				
		return resultado;
	}
}
