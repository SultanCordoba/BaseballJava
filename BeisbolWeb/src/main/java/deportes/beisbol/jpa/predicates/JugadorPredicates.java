package deportes.beisbol.jpa.predicates;

import com.mysema.query.types.Predicate;

import deportes.beisbol.jpa.model.QJugador;

public class JugadorPredicates {
	public static Predicate nombreIsLike(final String searchTerm) {
		QJugador jugador = QJugador.jugador;
				
		return jugador.nombres.startsWithIgnoreCase(searchTerm)
				.or(jugador.apellidoPaterno.startsWithIgnoreCase(searchTerm))
				.or(jugador.apellidoMaterno.startsWith(searchTerm))
				.or(jugador.nombreBusqueda.startsWithIgnoreCase(searchTerm));
	}
}
