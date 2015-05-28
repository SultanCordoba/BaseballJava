package deportes.beisbol.jpa.services;

import java.util.Optional;

import deportes.beisbol.jpa.model.Temporada;
import deportes.beisbol.web.model.TemporadaModel;

public interface TemporadaService {
	/* public Optional<TemporadaBeisbol> findById(short id);
	
	public Optional<TemporadaBeisbol> findByNombreAndLiga(String nombreTemporada, String ligaSiglas);
	
	public Optional<LigaBeisbol> findLigaPorTemporada(short id, Optional<String> idioma); */
	
	public Optional<Temporada> findOneBd(short id); 
	
	public TemporadaModel crearTemporadaModel(Short id, Optional<String> idioma);
}
