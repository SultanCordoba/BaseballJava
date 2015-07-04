package deportes.beisbol.jpa.services;

import java.util.Optional;

import deportes.beisbol.web.model.FranquiciaModel;

public interface FranquiciaService {	
	public FranquiciaModel creaFranquiciaModelo(Short id, Optional<String> idioma);
} 
