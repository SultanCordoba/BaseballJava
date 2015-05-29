package deportes.beisbol.jpa.services;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deportes.beisbol.converter.FranquiciaConverter;
import deportes.beisbol.converter.LigaConverter;
import deportes.beisbol.jpa.model.LigaHistorico;
import deportes.beisbol.jpa.repository.FranquiciaRepository;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.utils.RecordEtapa;
import deportes.beisbol.web.model.FranquiciaModel;

@Service
@Transactional(readOnly = true)
public class FranquiciaServiceImpl implements FranquiciaService {

	@Autowired
	FranquiciaRepository franquiciaRepository;
	
	@Autowired
	RecordService recordService;

	private LinkedHashSet<RecordEtapa> condensarRecords(LinkedHashSet<RecordEtapa> temporadas) {
		LinkedHashSet<RecordEtapa> resultado = new LinkedHashSet<RecordEtapa>();
		
		String temporadaActual = "XXXX";
		String etapaActual = "XXXX";
		
		Iterator<RecordEtapa> iteraRecords = temporadas.iterator();
		RecordEtapa paso;
		RecordEtapa condensado = null;
		
		while (iteraRecords.hasNext()) {
			paso = iteraRecords.next();
			
			if (!paso.getTemporadaNombre().equalsIgnoreCase(temporadaActual)) {
				
				if (!temporadaActual.endsWith("XXXX")) {
					resultado.add(condensado);
				}
				
				temporadaActual = paso.getTemporadaNombre();
				etapaActual = paso.getEtapaNombre();
				
				condensado = new RecordEtapa();
				condensado.setTemporadaNombre(temporadaActual);
				condensado.setEtapaNombre(etapaActual);
				condensado.setGanados(paso.getGanados());
				condensado.setPerdidos(paso.getPerdidos());
				condensado.setCampeon(paso.isCampeon());
				condensado.setParticipanteId(paso.getParticipanteId());
			}
			else {
				condensado.setGanados(condensado.getGanados() + paso.getGanados());
				condensado.setPerdidos(condensado.getPerdidos() + paso.getPerdidos());
			}
		}
		
		if (!temporadaActual.endsWith("XXXX")) {
			resultado.add(condensado);
		}
		
		return resultado;
	}

	
	@Override
	public FranquiciaModel creaFranquiciaModelo(Short id,
			Optional<String> idioma) {
		
		FranquiciaModel resultado = new FranquiciaModel();
		
		Optional<FranquiciaBeisbol> franquiciaBeisbol = 
			Optional.of(FranquiciaConverter.convierteDeBase(franquiciaRepository.findOne(id), idioma));

		resultado.setFranquicia(franquiciaBeisbol.get());
		resultado.setPais(franquiciaBeisbol.get().getPais());
		
		Iterator<LigaHistorico> iteraLigaHistorico = franquiciaRepository.findOne(id).getLiga().getLigaHistoricos().iterator();
		LigaHistorico ligaHist = null;
		
		while (iteraLigaHistorico.hasNext()) {
			ligaHist = iteraLigaHistorico.next();
		}
		
		Optional<LigaBeisbol> ligaBeisbol = Optional.ofNullable(LigaConverter.convierteDeBase(ligaHist, idioma));

		resultado.setLiga(ligaBeisbol.get());
		
		LinkedHashSet<RecordEtapa> temporadasEquipo = (LinkedHashSet<RecordEtapa>) 
				recordService.findTemporadasEquipos(franquiciaBeisbol.get().getId(), idioma);

		resultado.setTemporadas(condensarRecords(temporadasEquipo));

		return resultado;
	}
	
	/* @Override
	public Optional<FranquiciaBeisbol> findById(short id, Optional<String> idioma) {
		
		FranquiciaBeisbol resultado = null;
		
		resultado = FranquiciaConverter.convierteDeBase(franquiciaRepository.findOne(id), idioma);
		
		return Optional.ofNullable(resultado);
	}

	@Override
	public Collection<FranquiciaBeisbol> findByLigaId(Byte id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<LigaBeisbol> findLigaPorFranquicia(short id,
			Optional<String> idioma) {
		// Franquicia resultado = null;
		
		// logger.info(resultado.getLigaHistorico().getSiglas());
		
		// LigaHistorico resultado;
		
		Iterator<LigaHistorico> iteraLigaHistorico = franquiciaRepository.findOne(id).getLiga().getLigaHistoricos().iterator();
		LigaHistorico resultado = null;
		
		while (iteraLigaHistorico.hasNext()) {
			resultado = iteraLigaHistorico.next();
		}
		
		return Optional.ofNullable(LigaConverter.convierteDeBase(resultado, idioma));
	}

	@Override
	public Optional<Franquicia> findOneBd(short id) {
		return Optional.ofNullable(franquiciaRepository.findOne(id));
	} */

}
