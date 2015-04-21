package deportes.beisbol.converter;

import deportes.beisbol.jpa.model.Temporada;
import deportes.beisbol.model.TemporadaBeisbol;

public class TemporadaConverter {
	public static TemporadaBeisbol convierteDeBase(Temporada temporadaBase) {
		TemporadaBeisbol resultado = new TemporadaBeisbol();
		
		resultado.setId(temporadaBase.getId());
		resultado.setNombre(temporadaBase.getNombre());
		
		return resultado;
	}
}
