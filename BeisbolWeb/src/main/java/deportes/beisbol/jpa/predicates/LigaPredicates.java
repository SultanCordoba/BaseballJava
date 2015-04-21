package deportes.beisbol.jpa.predicates;

import com.mysema.query.types.Predicate;

import deportes.beisbol.jpa.model.QLigaHistorico;

public class LigaPredicates {

	public static Predicate nombreIsLike(final String searchTerm, String idioma) {
		QLigaHistorico ligaHistorico = QLigaHistorico.ligaHistorico;
		
		return ligaHistorico.nombre.containsIgnoreCase(searchTerm);
		
	}
	
	public static Predicate siglasIsLike(final String searchTerm, String idioma) {
		QLigaHistorico ligaHistorico = QLigaHistorico.ligaHistorico;
		
	    return ligaHistorico.siglas.containsIgnoreCase(searchTerm);
	}
}
