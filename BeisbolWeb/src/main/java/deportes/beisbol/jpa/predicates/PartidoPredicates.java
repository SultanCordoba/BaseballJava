package deportes.beisbol.jpa.predicates;



import java.util.Date;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

import deportes.beisbol.jpa.model.QJugador;
import deportes.beisbol.jpa.model.QPartido;
import deportes.beisbol.jpa.model.QPartidoEquipo;

public class PartidoPredicates {
	
	public static OrderSpecifier<Short> etapaIdOrder() {
		return QPartido.partido.etapa.id.asc();
	}
	
	public static OrderSpecifier<Date> fechaRealizacionOrder() {
		return QPartido.partido.fechaRealizacion.asc();
	}
	
	public static Predicate ligaIsLike(final String ligaSiglas) {
		QPartido partido = QPartido.partido;
		
		return partido.etapa.temporada.ligaHistorico.siglas.containsIgnoreCase(ligaSiglas);
	}
	
	public static Predicate equipoIsLike(final String equipo) {
		QPartidoEquipo partidoEquipo = QPartidoEquipo.partidoEquipo;

		return partidoEquipo.equipo.nombreTablasEs.containsIgnoreCase(equipo);
	}
	
	public static Predicate partidoTemporadaIs(final Short temporadaId) {
		QPartido partido = QPartido.partido;
		
		return partido.etapa.temporada.id.eq(temporadaId);
	}
}
