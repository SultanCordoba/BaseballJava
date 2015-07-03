package deportes.beisbol.jpa.predicates;

import com.mysema.query.types.Predicate;

import deportes.beisbol.jpa.model.QEquipo;

public class EquipoPredicates {

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
}
