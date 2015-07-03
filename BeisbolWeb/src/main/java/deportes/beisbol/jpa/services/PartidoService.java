package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Optional;

import deportes.beisbol.model.PartidoBeisbol;

public interface PartidoService {
	public boolean save(PartidoBeisbol partido, short etapaId, byte vueltaId);
	
	public PartidoBeisbol findOne(Short id);
	
	public Collection<PartidoBeisbol> findByTemporada(Short temporadaId, Optional<String> idioma);
}
