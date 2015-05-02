package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deportes.beisbol.converter.RecordConverter;
import deportes.beisbol.jpa.model.Record;
import deportes.beisbol.jpa.repository.RecordRepository;
import deportes.beisbol.utils.TemporadaEquipo;

@Service
@Transactional(readOnly = true)
public class RecordServiceImpl implements RecordService {

	@Autowired
	RecordRepository recordRepository;
	
	@Override
	public Collection<TemporadaEquipo> findTemporadasEquipos(Short franquiciaId) {

		Iterator<Record> iteraRecord = recordRepository.findByParticipanteOrdenados(franquiciaId).iterator();
		Record recordBase = null;
		
		LinkedHashSet<TemporadaEquipo> resultado = new LinkedHashSet<>();
		
		while (iteraRecord.hasNext()) {
			recordBase = iteraRecord.next();
			
			resultado.add(RecordConverter.convierteAuxDeBase(recordBase));
		}
		
		return resultado;
	}
}
