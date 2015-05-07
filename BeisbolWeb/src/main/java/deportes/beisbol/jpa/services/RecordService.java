package deportes.beisbol.jpa.services;

import deportes.beisbol.utils.TemporadaEquipo;

import java.util.Collection;
import java.util.Optional;

public interface RecordService {
	public Collection<TemporadaEquipo> findTemporadasEquipos(Short franquiciaId, Optional<String> idioma);
	
	
}
