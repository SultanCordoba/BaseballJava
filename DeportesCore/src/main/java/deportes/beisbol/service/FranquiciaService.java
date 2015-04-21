package deportes.beisbol.service;

import java.util.Collection;
import java.util.Optional;

import deportes.beisbol.model.FranquiciaBeisbol;

public interface FranquiciaService {
	public Optional<FranquiciaBeisbol> findById(short id);
	
	public Collection<FranquiciaBeisbol> findByLigaId(Byte id);
}
