package deportes.beisbol.jpa.predicates;

import java.util.Date;

/* import org.slf4j.Logger;
import org.slf4j.LoggerFactory; */

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

import deportes.beisbol.jpa.model.QRoster;

public class RosterPredicates {
	
	//private static final Logger logger = LoggerFactory.getLogger(RosterPredicates.class);

	public static OrderSpecifier<Date> orderByFechaInicioAsc() {
		return QRoster.roster.fechaInicio.asc();
	}
	
	public static Predicate rosterJugadorLigaActiva(Short jugadorId) {
		QRoster roster = QRoster.roster;
		
		return roster.jugador.id.eq(jugadorId).and
				(roster.equipo.franquiciaHistorico.franquicia.liga.activa.eq((byte) 1));
	}
	
	public static Predicate rosterJugadorFechas(Short jugadorId, Date fechaInicio, Date fechaFin) {
		QRoster roster = QRoster.roster;
		
		return roster.jugador.id.eq(jugadorId).and
				(roster.fechaInicio.before(fechaFin)
					.or(roster.fechaInicio.eq(fechaFin))).and
				(roster.fechaFin.after(fechaInicio)
					.or(roster.fechaFin.eq(fechaInicio)));
	}
	
	public static Predicate rosterEquipo(Short equipoId) {
		QRoster roster = QRoster.roster;
		
		return roster.equipo.id.eq(equipoId);
	}
}
