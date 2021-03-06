package deportes.beisbol.jpa.predicates;

import java.util.Optional;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

import deportes.beisbol.jpa.model.QEquipo;

public class EquipoPredicates {

    public static OrderSpecifier<String> orderByNombreAsc() {
        return QEquipo.equipo.nombreCompletoEs.asc();
    }
	
	public static Predicate campeonTemporada(Short temporadaId) {
		QEquipo equipo = QEquipo.equipo;
		
		return equipo.participante.temporada.id.eq(temporadaId)
				.and(equipo.campeon.eq((byte) 1));
	}
	
	public static Predicate equipoLiga(String siglasEquipo, String siglasLiga, Short temporadaId) {
		QEquipo equipo = QEquipo.equipo;
		
		return equipo.abreviatura.equalsIgnoreCase(siglasEquipo).and
				(equipo.franquiciaHistorico.franquicia.liga.siglasEs.equalsIgnoreCase(siglasLiga)).and
				(equipo.participante.temporada.id.eq(temporadaId));
	}
	
	public static Predicate equiposLiga(String siglasLiga, String equipoStr, Optional<String> idioma) {
		QEquipo equipo = QEquipo.equipo;
		
		return equipo.franquiciaHistorico.franquicia.liga.siglasEs.equalsIgnoreCase(siglasLiga).and
				(equipo.nombreCompletoEs.containsIgnoreCase(equipoStr));
	}
}
