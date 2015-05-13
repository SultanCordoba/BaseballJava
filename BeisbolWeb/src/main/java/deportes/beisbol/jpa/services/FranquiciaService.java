package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Optional;

import deportes.beisbol.jpa.model.Franquicia;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.LigaBeisbol;

public interface FranquiciaService {
	public Optional<FranquiciaBeisbol> findById(short id);
	
	public Collection<FranquiciaBeisbol> findByLigaId(Byte id);
	
	public Optional<LigaBeisbol> findLigaPorFranquicia(short id, Optional<String> idioma);
	
	public Optional<Franquicia> findOneBd(short id);
} 
