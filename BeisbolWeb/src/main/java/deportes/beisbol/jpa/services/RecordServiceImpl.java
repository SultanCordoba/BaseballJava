package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deportes.beisbol.converter.RecordConverter;
import deportes.beisbol.jpa.model.EtapaInt;
import deportes.beisbol.jpa.model.Record;
import deportes.beisbol.jpa.repository.RecordRepository;
import deportes.beisbol.utils.RecordEtapa;

@Service
@Transactional(readOnly = true)
public class RecordServiceImpl implements RecordService {

	@Autowired
	RecordRepository recordRepository;
		
	private LinkedHashSet<RecordEtapa> convierteRecords(Iterator<Record> iteraRecord, Optional<String> idioma) {
		Record recordBase = null;
		
		LinkedHashSet<RecordEtapa> resultado = new LinkedHashSet<>();
		RecordEtapa tempEquipo = null;
		
		while (iteraRecord.hasNext()) {
			recordBase = iteraRecord.next();
			
			tempEquipo = RecordConverter.convierteAuxDeBase(recordBase);
			
			if (idioma.isPresent()) {
				String idiomaString = idioma.get().toUpperCase();
				
				if (!idiomaString.equals("ES")) {
					Iterator<EtapaInt> etapasInt = recordBase.getEtapa().getEtapaInts().iterator();
					EtapaInt etapaInt;
					
					while (etapasInt.hasNext()) {
						etapaInt = etapasInt.next();
						
						if (etapaInt.getIdioma().getAbreviatura().equalsIgnoreCase(idiomaString)) {
							tempEquipo.setEtapaNombre(etapaInt.getNombre());
						}
					}
					
				}
			}
			
			resultado.add(tempEquipo);
		}
		
		return resultado;		
	}
	
	
	@Override
	public Collection<RecordEtapa> findTemporadasEquipos(Short franquiciaId, Optional<String> idioma) {

		Iterator<Record> iteraRecord = recordRepository.findByParticipanteOrdenados(franquiciaId).iterator();
		
		return convierteRecords(iteraRecord, idioma);
	}


	@Override
	public Collection<RecordEtapa> findEtapaEquipo(Short franquiciaId,
			Short temporadaId, Optional<String> idioma) {

		Iterator<Record> iteraRecord = recordRepository.findByParticipanteTemporada(franquiciaId, temporadaId).iterator();
		
		return convierteRecords(iteraRecord, idioma);
	}
}
