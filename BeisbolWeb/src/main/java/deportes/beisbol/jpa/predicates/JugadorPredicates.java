package deportes.beisbol.jpa.predicates;

import java.util.Locale;
import java.util.Optional;

/* import org.slf4j.Logger;
import org.slf4j.LoggerFactory; */

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

import deportes.beisbol.jpa.model.QJugador;
import deportes.beisbol.jpa.model.QLigaHistorico;

public class JugadorPredicates {
	
	//private static final Logger logger = LoggerFactory.getLogger(JugadorPredicates.class);
	
    public static OrderSpecifier<String> orderByNombreAsc() {
        return QLigaHistorico.ligaHistorico.nombre.asc();
    }
	
	public static Predicate jugadorIsLike(final String searchTerm, Optional<String> idioma) {
		QJugador jugador = QJugador.jugador;
		
		BooleanBuilder condiciones = new BooleanBuilder();
		BooleanBuilder condicionFecha = new BooleanBuilder();
		
		condiciones.or(jugador.nombreBusqueda.containsIgnoreCase(searchTerm));
				
		ValidadorFecha validadorFecha = new ValidadorFecha(searchTerm);
		validadorFecha.setLocale(new Locale.Builder().setLanguage(idioma.get()).build());
		
		if (validadorFecha.getDia() > 0) {
			// condicionFecha.and(jugador.fechaNacimiento.dayOfMonth().eq(validadorFecha.getDia()));
			condicionFecha.and(jugador.fechaNacimiento.dayOfMonth().stringValue().contains(String.valueOf(validadorFecha.getDia())));
		}
		
		if (validadorFecha.getMes() > 0) {
			//condicionFecha.and(jugador.fechaNacimiento.month().eq(validadorFecha.getMes()));
			condicionFecha.and(jugador.fechaNacimiento.month().in(validadorFecha.getMes()));
		}
		
		if (validadorFecha.getAnyo() > 0) {
			//condicionFecha.and(jugador.fechaNacimiento.year().eq(validadorFecha.getAnyo()));
			condicionFecha.and(jugador.fechaNacimiento.year().stringValue().contains(String.valueOf(validadorFecha.getAnyo())));
		}
		
		Predicate resultado = condiciones.or(condicionFecha);
		
		return resultado;
		
		/* return jugador.nombres.startsWithIgnoreCase(searchTerm)
				.or(jugador.apellidoPaterno.startsWithIgnoreCase(searchTerm))
				.or(jugador.apellidoMaterno.startsWithIgnoreCase(searchTerm))
				.or(jugador.nombreBusqueda.startsWithIgnoreCase(searchTerm)); */
	}
}
