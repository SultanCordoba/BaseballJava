package deportes.beisbol.converter;

import java.util.Optional;

import deportes.beisbol.jpa.model.LigaHistorico;
import deportes.beisbol.jpa.model.LigaHistoricoInt;
import deportes.beisbol.model.LigaBeisbol;

public class LigaConverter {
	
	// private static final Logger logger = LoggerFactory.getLogger(LigaConverter.class);
	
	public static LigaBeisbol convierteDeBase(LigaHistorico ligaBase, String idioma) {
		LigaBeisbol resultado = new LigaBeisbol();
		
		resultado.setId(ligaBase.getLiga().getId());
		resultado.setNombre(ligaBase.getNombre());
		resultado.setSiglas(ligaBase.getSiglas());			
		
		Optional<LigaHistoricoInt> ligaHistoricoInt = 
		  ligaBase.getLigaHistoricoInts().stream()
		  .filter(lh -> lh.getIdioma().getAbreviatura().equalsIgnoreCase(idioma))
		  .findFirst();
		  
		if (ligaHistoricoInt.isPresent()) {
			resultado.setNombre(ligaHistoricoInt.get().getNombre());
			resultado.setSiglas(ligaHistoricoInt.get().getSiglas());
		}
		
		return resultado;
	}
}
