package deportes.beisbol.converter;

import java.util.Iterator;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

import deportes.beisbol.jpa.model.LigaHistorico;
import deportes.beisbol.jpa.model.LigaHistoricoInt;
import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.web.controller.LigaController;

public class LigaConverter {
	
	// private static final Logger logger = LoggerFactory.getLogger(LigaConverter.class);
	
	public static LigaBeisbol convierteDeBase(LigaHistorico ligaBase, Optional<String> idioma) {
		LigaBeisbol resultado = new LigaBeisbol();
		
		resultado.setId(ligaBase.getLiga().getId());
		
		String idiomaAbrev = Strings.nullToEmpty(idioma.get()).toUpperCase();

		resultado.setNombre(ligaBase.getNombre());
		resultado.setSiglas(ligaBase.getSiglas());			
		
		// logger.info("idiomaAbrev: " + idiomaAbrev);
		
		if (!idiomaAbrev.equals("ES")) {
			Iterator<LigaHistoricoInt> ligasHistoricoInt = ligaBase.getLigaHistoricoInts().iterator();
			LigaHistoricoInt ligaHistoricoInt;
			
			while (ligasHistoricoInt.hasNext()) {
				ligaHistoricoInt = ligasHistoricoInt.next();
				
				// logger.info("Idioma Int:" + ligaHistoricoInt.getIdioma().getAbreviatura());
				
				if (ligaHistoricoInt.getIdioma().getAbreviatura().toUpperCase().equals(idiomaAbrev)) {
					resultado.setNombre(ligaHistoricoInt.getNombre());
					resultado.setSiglas(ligaHistoricoInt.getSiglas());
				}
			}
		}
		
		return resultado;
	}
	
	/* public LigaHistorico convierteDeObjeto(LigaBeisbol ligaBase) {
		LigaHistorico resultado = new LigaHistorico();
		
		
		return resultado;
	} */
}
