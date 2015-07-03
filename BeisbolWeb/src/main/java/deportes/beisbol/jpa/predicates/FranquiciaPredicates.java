package deportes.beisbol.jpa.predicates;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

import deportes.beisbol.jpa.model.QFranquicia;

public class FranquiciaPredicates {
	public static OrderSpecifier<String> orderByFechaInicioDesc() {
		return QFranquicia.franquicia.nombreTablasEs.asc();
	}

	public static Predicate isLigaId(Byte ligaId) {
		QFranquicia franquicia = QFranquicia.franquicia;
		
		return franquicia.liga.id.eq(ligaId);
	}
}
