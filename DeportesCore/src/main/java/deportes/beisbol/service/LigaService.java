package deportes.beisbol.service;

import java.util.Collection;
import java.util.Optional;

import deportes.beisbol.model.LigaBeisbol;

public interface LigaService {
	public Optional<LigaBeisbol> findOne(Byte id, Optional<String> idioma);
	 
	public Optional<LigaBeisbol> findBySiglas(String siglas, Optional<String> idioma);
	
	public Collection<LigaBeisbol> findLikeNombre(String nombre, Optional<String> idioma);
	
	public Collection<LigaBeisbol> findAll(Optional<String> idioma);
	
	public Collection<LigaBeisbol> findActivas(Optional<String> idioma);
}
