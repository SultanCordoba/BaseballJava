package deportes.beisbol.jpa.predicates;

import java.util.Date;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

import deportes.beisbol.jpa.model.LigaHistorico;
import deportes.beisbol.jpa.model.QTemporada;

public class TemporadaPredicates {

	public static OrderSpecifier<Date> orderByFechaInicioDesc() {
		return QTemporada.temporada.fechaInicio.desc();
	}
	
	public static Predicate ligaHistoricoIs(LigaHistorico lh) {
		QTemporada temporada = QTemporada.temporada;
		
		return temporada.ligaHistorico.eq(lh);
	}
	
	public static Predicate nombreIs(String nombre) {
		QTemporada temporada = QTemporada.temporada;
		
		return temporada.nombre.equalsIgnoreCase(nombre);
	}
	
	public static Predicate fechaBetween(Date fecha) {
		QTemporada temporada = QTemporada.temporada;
		
		return temporada.fechaInicio.before(fecha)
				.and(temporada.fechaFin.after(fecha));
	}
}
