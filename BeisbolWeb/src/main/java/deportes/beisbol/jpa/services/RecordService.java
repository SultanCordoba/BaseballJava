package deportes.beisbol.jpa.services;

import deportes.beisbol.utils.RecordEtapa;

import java.util.Collection;
import java.util.Optional;

public interface RecordService {
	public Collection<RecordEtapa> findTemporadasEquipos(Short franquiciaId, Optional<String> idioma);
	
	
}
