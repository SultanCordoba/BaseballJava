package deportes.beisbol.service;

import java.util.Collection;
import java.util.Optional;

import deportes.beisbol.model.EquipoBeisbol;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.TemporadaBeisbol;

public interface EquipoService {
	public Optional<EquipoBeisbol> findCampeon(TemporadaBeisbol t);
	
	public Optional<EquipoBeisbol> findOne(Short id);
	
	public Collection<EquipoBeisbol> findByFranquicia(FranquiciaBeisbol f);

}
