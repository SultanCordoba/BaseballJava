package deportes.beisbol.converter;

import java.util.Optional;

import deportes.beisbol.jpa.model.Record;
import deportes.beisbol.model.RecordBeisbol;

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
}
