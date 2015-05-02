package deportes.beisbol.jpa.services;

import deportes.beisbol.utils.TemporadaEquipo;

import java.util.Collection;

public interface RecordService {
	public Collection<TemporadaEquipo> findTemporadasEquipos(Short franquiciaId);
}
