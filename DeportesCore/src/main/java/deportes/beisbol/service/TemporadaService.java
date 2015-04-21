package deportes.beisbol.service;

import java.util.Optional;

import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.model.TemporadaBeisbol;

public interface TemporadaService {
	public Optional<TemporadaBeisbol> findById(short id);
	
	public Optional<TemporadaBeisbol> findByNombreAndLiga(String nombreTemporada, String ligaSiglas);
	
	public Optional<LigaBeisbol> findLigaPorTemporada(short id, Optional<String> idioma);
}
