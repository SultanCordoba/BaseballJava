package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Optional;

import deportes.beisbol.jpa.model.Liga;
import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.web.model.LigaModel;

public interface LigaService {	
	public Optional<Liga> findOneBd(Byte id);
	
	public Collection<LigaBeisbol> getAllLigas(String idioma);
	
	public LigaModel creaLigaModel(Byte id, String idioma);
}
