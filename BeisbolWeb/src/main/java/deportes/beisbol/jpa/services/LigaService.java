package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Optional;

import deportes.beisbol.jpa.model.Liga;
import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.web.model.LigaModel;

public interface LigaService {
	/* public Optional<LigaBeisbol> findOne(Byte id, Optional<String> idioma);
	 
	public Optional<LigaBeisbol> findBySiglas(String siglas, Optional<String> idioma);
	
	public Collection<LigaBeisbol> findLikeNombre(String nombre, Optional<String> idioma);
	
	public Collection<LigaBeisbol> findAll(Optional<String> idioma);
	
	public Collection<LigaBeisbol> findActivas(Optional<String> idioma); */
	
	public Optional<Liga> findOneBd(Byte id);
	
	public Collection<LigaBeisbol> getAllLigas(Optional<String> idioma);
	
	public LigaModel creaLigaModel(Byte id, Optional<String> idioma);
}
