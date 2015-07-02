package deportes.beisbol.jpa.predicates;



import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

import deportes.beisbol.jpa.model.QJugador;
import deportes.beisbol.jpa.model.QLigaHistorico;

public class JugadorPredicates {
	
    public static OrderSpecifier<String> orderByNombreAsc() {
        return QLigaHistorico.ligaHistorico.nombre.asc();
    }
	
	public static Predicate nombreIsLike(final String searchTerm) {
		QJugador jugador = QJugador.jugador;
		
		/* return jugador.nombreCompleto.startsWithIgnoreCase(searchTerm)
				.or(jugador.nombreBusqueda.startsWithIgnoreCase(searchTerm)); */
		
		return jugador.nombreBusqueda.containsIgnoreCase(searchTerm);
		
		/* return jugador.nombres.startsWithIgnoreCase(searchTerm)
				.or(jugador.apellidoPaterno.startsWithIgnoreCase(searchTerm))
				.or(jugador.apellidoMaterno.startsWithIgnoreCase(searchTerm))
				.or(jugador.nombreBusqueda.startsWithIgnoreCase(searchTerm)); */
	}
}
