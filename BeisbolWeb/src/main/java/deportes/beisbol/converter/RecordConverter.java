package deportes.beisbol.converter;

import java.util.Iterator;
import java.util.Optional;

import deportes.beisbol.jpa.model.Equipo;
import deportes.beisbol.jpa.model.Record;
import deportes.beisbol.model.RecordBeisbol;
import deportes.beisbol.utils.TemporadaEquipo;

public class RecordConverter {
	public static RecordBeisbol convierteDeBase(Record recordBase, Optional<String> idioma) {
		RecordBeisbol resultado = new RecordBeisbol();
		
		resultado.setNombreGrupo(recordBase.getNombreGrupo());
		resultado.setGanados(recordBase.getGanados());
		resultado.setPerdidos(recordBase.getPerdidos());
		resultado.setPorcentaje(recordBase.getPerdidos() > 0 ? 
				recordBase.getGanados() / ((double) recordBase.getGanados() + recordBase.getPerdidos()) : 0);
		resultado.setIdVuelta(recordBase.getVuelta().getId());
		resultado.setNombreVuelta(recordBase.getVuelta().getNombre());
		
		return resultado;
	}
	
	public static TemporadaEquipo convierteAuxDeBase(Record recordBase) {
		TemporadaEquipo resultado = new TemporadaEquipo();
		
		resultado.setTemporadaNombre(recordBase.getParticipante().getTemporada().getNombre());
		String etapaNombre = recordBase.getEtapa().getNombre();
		
		resultado.setEtapaNombre(etapaNombre);
		
		resultado.setGanados(recordBase.getGanados());
		resultado.setPerdidos(recordBase.getPerdidos());
		resultado.setCampeon(false);
		
		Iterator<Equipo> equipos = recordBase.getParticipante().getEquipos().iterator();
		Equipo equipoPaso;
		
		while (equipos.hasNext()) {
			equipoPaso = equipos.next();
			
			if (equipoPaso.getCampeon() == 1) {
				resultado.setCampeon(true);
				break;
			}
		}
		
		return resultado;
	}
}
