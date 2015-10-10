package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Optional;

import deportes.beisbol.model.EquipoBeisbol;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.TemporadaBeisbol;
import deportes.beisbol.utils.EquipoPais;
import deportes.beisbol.utils.PaginaDefinidor;
import deportes.beisbol.web.model.EquipoModel;

public interface EquipoService {
	public Optional<EquipoBeisbol> findCampeon(TemporadaBeisbol t, Optional<String> idioma);
	
	public Optional<EquipoBeisbol> findOne(Short id, Optional<String> idioma);
	
	public Collection<EquipoBeisbol> findByFranquicia(FranquiciaBeisbol f);
	
	public EquipoModel creaEquipoModel(Short id, Optional<String> idioma);
	
	public Collection<EquipoBeisbol> search(String siglasLiga, String nombre, PaginaDefinidor pagina, Optional<String> idioma);
	
	public short totalRegistros();
	
	public short totalRegistros(String siglasLiga, String nombre, Optional<String> idioma);
	
	public LinkedHashMap<String, EquipoPais> busqueda(String siglasLiga, String nombre, PaginaDefinidor pagina, Optional<String> idioma);
}
