package deportes.beisbol.jpa.services;

import java.util.Optional;

import deportes.beisbol.web.model.FranquiciaModel;

public interface FranquiciaService {
	/* public Optional<FranquiciaBeisbol> findById(short id, Optional<String> idioma);
	
	public Collection<FranquiciaBeisbol> findByLigaId(Byte id);
	
	public Optional<LigaBeisbol> findLigaPorFranquicia(short id, Optional<String> idioma);
	
	public Optional<Franquicia> findOneBd(short id); */
	
	public FranquiciaModel creaFranquiciaModelo(Short id, Optional<String> idioma);
} 
