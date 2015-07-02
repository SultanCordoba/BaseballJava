package deportes.beisbol.jpa.predicates;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

import deportes.beisbol.jpa.model.QLigaHistorico;

public class LigaPredicates {

    public static OrderSpecifier<String> orderByNombreAsc() {
        return QLigaHistorico.ligaHistorico.nombre.asc();
    }
	
	public static Predicate nombreIsLike(final String searchTerm, String idioma) {
		QLigaHistorico ligaHistorico = QLigaHistorico.ligaHistorico;
		
		return ligaHistorico.nombre.containsIgnoreCase(searchTerm);
	}
	
	public static Predicate siglasIsLike(final String searchTerm, String idioma) {
		QLigaHistorico ligaHistorico = QLigaHistorico.ligaHistorico;
		
	    return ligaHistorico.siglas.containsIgnoreCase(searchTerm);
	}
	
	public static Predicate idIs(final Byte id) {
		QLigaHistorico ligaHistorico = QLigaHistorico.ligaHistorico;
		
		return ligaHistorico.liga.id.eq(id);
	}
	
	public static Predicate ligasActivas() {
		QLigaHistorico ligaHistorico = QLigaHistorico.ligaHistorico;
		
		return ligaHistorico.liga.activa.eq((byte) 1);
	}
}
