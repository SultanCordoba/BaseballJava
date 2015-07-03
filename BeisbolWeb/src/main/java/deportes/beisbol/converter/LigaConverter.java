package deportes.beisbol.converter;

import java.util.Iterator;
import java.util.Optional;

import com.google.common.base.Strings;

import deportes.beisbol.jpa.model.LigaHistorico;
import deportes.beisbol.jpa.model.LigaHistoricoInt;
import deportes.beisbol.model.LigaBeisbol;

public class LigaConverter {
	
	// private static final Logger logger = LoggerFactory.getLogger(LigaConverter.class);
	
	public static LigaBeisbol convierteDeBase(LigaHistorico ligaBase, Optional<String> idioma) {
		LigaBeisbol resultado = new LigaBeisbol();
		
		resultado.setId(ligaBase.getLiga().getId());
		
		String idiomaAbrev = Strings.nullToEmpty(idioma.get()).toUpperCase();

		resultado.setNombre(ligaBase.getNombre());
		resultado.setSiglas(ligaBase.getSiglas());			
		
		if (!idiomaAbrev.equals("ES")) {
			Iterator<LigaHistoricoInt> ligasHistoricoInt = ligaBase.getLigaHistoricoInts().iterator();
			LigaHistoricoInt ligaHistoricoInt;
			
			while (ligasHistoricoInt.hasNext()) {
				ligaHistoricoInt = ligasHistoricoInt.next();
				
				if (ligaHistoricoInt.getIdioma().getAbreviatura().toUpperCase().equals(idiomaAbrev)) {
					resultado.setNombre(ligaHistoricoInt.getNombre());
					resultado.setSiglas(ligaHistoricoInt.getSiglas());
				}
			}
		}
		
		return resultado;
	}
}
