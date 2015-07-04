package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Optional;

import deportes.beisbol.jpa.model.Temporada;
import deportes.beisbol.web.model.TemporadaActual;
import deportes.beisbol.web.model.TemporadaModel;

public interface TemporadaService {
	public Optional<Temporada> findOneBd(short id); 
	
	public TemporadaModel crearTemporadaModel(Short id, Optional<String> idioma);
	
	public Collection<TemporadaActual> buscarActuales();
} 
