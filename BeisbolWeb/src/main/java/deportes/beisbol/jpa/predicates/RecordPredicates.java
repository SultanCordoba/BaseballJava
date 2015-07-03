package deportes.beisbol.jpa.predicates;

import java.util.Date;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.Predicate;

import deportes.beisbol.jpa.model.QRecord;

public class RecordPredicates {
	
	public static OrderSpecifier<Date> fechaInicioOrder() {
		return QRecord.record.etapa.temporada.fechaInicio.asc();
	}
	
	public static OrderSpecifier<Short> etapaIdDesc() {
		return QRecord.record.etapa.id.desc();
	}
	
	public static Predicate participantesOrdenados(Short franquiciaId) {
		QRecord record = QRecord.record;
		
		return record.participante.equipos.any().franquiciaHistorico.franquicia.id.eq(franquiciaId);
	}

}
