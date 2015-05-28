package deportes.beisbol.component;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deportes.beisbol.jpa.services.LigaService;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.model.RangoFechaBeisbol;
import deportes.beisbol.utils.EquipoPais;
import deportes.beisbol.web.model.LigaModel;

@Service
public class LigaComponent {

	@Autowired
	LigaService ligaService;
	
	public Collection<LigaBeisbol> getAllLigas(Optional<String> idioma) {
		return ligaService.findActivas(idioma);
	}
	
	@SuppressWarnings("unchecked")
	public LigaModel creaLigaModel(Byte id, Optional<String> idioma) {
		LigaModel resultado = new LigaModel();
		
        Optional<LigaBeisbol> ligaBeisbol;
		
		ligaBeisbol = ligaService.findOne(id, idioma);		
		//resultado.orElseThrow(() -> new LigaNotFoundException(id));
		
		Iterator<FranquiciaBeisbol> iteradorFranquicias = (Iterator<FranquiciaBeisbol>) 
				ligaBeisbol.get().getFranquicias().iterator();
		FranquiciaBeisbol franquicia = null;
		
		LinkedHashMap<String, EquipoPais> equipos = new LinkedHashMap<>();
		
		EquipoPais equipoAux = null;
		
		while (iteradorFranquicias.hasNext()) {
			franquicia = iteradorFranquicias.next();
			Iterator<RangoFechaBeisbol> nombres = (Iterator<RangoFechaBeisbol>) 
					franquicia.getNombres().iterator();
			
			while (nombres.hasNext()) {
				equipoAux = new EquipoPais();
				equipoAux.setIdFranquicia(franquicia.getId());
				equipoAux.setNombre(nombres.next().getNombre());
				
				equipoAux.setPais(franquicia.getPais());
				equipos.put(equipoAux.getNombre(), equipoAux);
			}
			
		}
				
		resultado.setLiga(ligaBeisbol.get());
		resultado.setEquipos(equipos.values());
		
		return resultado;
	}
}
