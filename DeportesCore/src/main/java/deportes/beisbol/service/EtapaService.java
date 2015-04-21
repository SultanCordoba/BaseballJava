package deportes.beisbol.service;

import java.util.Collection;
import java.util.Optional;

import deportes.beisbol.model.EtapaBeisbol;
import deportes.beisbol.model.TemporadaBeisbol;

public interface EtapaService {
	public Collection<EtapaBeisbol> findEtapasByTemporada(TemporadaBeisbol temporada, Optional<String> idioma);
}
