package deportes.beisbol.jpa.services;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;

/* import org.slf4j.Logger;
import org.slf4j.LoggerFactory; */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deportes.beisbol.converter.EtapaConverter;
import deportes.beisbol.converter.ParticipanteConverter;
import deportes.beisbol.converter.RecordConverter;
import deportes.beisbol.jpa.model.Etapa;
import deportes.beisbol.jpa.model.Record;
import deportes.beisbol.jpa.repository.EtapaRepository;
import deportes.beisbol.jpa.repository.TemporadaRepository;
import deportes.beisbol.model.EtapaBeisbol;
import deportes.beisbol.model.RecordBeisbol;
import deportes.beisbol.model.TemporadaBeisbol;
import deportes.beisbol.service.EtapaService;
import deportes.beisbol.utils.RecordComparator;

@Service
@Transactional(readOnly = true)
public class EtapaServiceImpl implements EtapaService {

	@Autowired
	EtapaRepository etapaRepository;
	
	@Autowired
	TemporadaRepository temporadaRepository;

	// private static final Logger logger = LoggerFactory.getLogger(EtapaServiceImpl.class);
	
	@Override
	public Collection<EtapaBeisbol> findEtapasByTemporada(
			TemporadaBeisbol temporada, Optional<String> idioma) {
		
		Iterator<Etapa> iteraEtapas = etapaRepository.findByTemporada(temporadaRepository.findOne(temporada.getId())).iterator();
		LinkedHashSet<EtapaBeisbol> resultado = new LinkedHashSet<>();
		
		Etapa etapa;
		EtapaBeisbol etapaBeisbol;
		Iterator<Record> iteraRecords;
		ArrayList<RecordBeisbol> records;
		RecordBeisbol recordBeisbol;
		Record recordBase;
		
		while (iteraEtapas.hasNext()) {
			etapa = iteraEtapas.next();
						
			etapaBeisbol = EtapaConverter.convierteDeBase(etapa, idioma);
			
			iteraRecords = etapa.getRecords().iterator();
			records = new ArrayList<>();
			
			while (iteraRecords.hasNext()) {
				recordBase = iteraRecords.next();
				recordBeisbol = RecordConverter.convierteDeBase(recordBase, idioma);
				
				// Agregar nombre del participante a recordBeisbol
				recordBeisbol.setNombre(ParticipanteConverter.nombreParticipante(recordBase.getParticipante(), false, idioma));
				recordBeisbol.setNombreAbrev(ParticipanteConverter.nombreParticipante(recordBase.getParticipante(), true, idioma));
				
				NumberFormat formatter = new DecimalFormat("0.000");
				recordBeisbol.setPctjeString(formatter.format(recordBeisbol.getPorcentaje()));
				recordBeisbol.setParticipanteId(recordBase.getParticipante().getId());
				records.add(recordBeisbol);
			}
			
			Collections.sort(records, new RecordComparator());
			
			
			etapaBeisbol.setRecords(new LinkedHashSet<RecordBeisbol>(records));
			
			resultado.add(etapaBeisbol);
		}
		
		return resultado;
	}
	


}
