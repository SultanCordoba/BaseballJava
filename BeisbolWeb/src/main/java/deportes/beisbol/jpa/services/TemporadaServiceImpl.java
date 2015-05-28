package deportes.beisbol.jpa.services;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;

/* import org.slf4j.Logger;
import org.slf4j.LoggerFactory; */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deportes.beisbol.converter.EtapaConverter;
import deportes.beisbol.converter.TemporadaConverter;
import deportes.beisbol.jpa.model.Temporada;
import deportes.beisbol.jpa.repository.LigaHistoricoRepository;
import deportes.beisbol.jpa.repository.TemporadaRepository;
import deportes.beisbol.model.EquipoBeisbol;
import deportes.beisbol.model.EtapaBeisbol;
import deportes.beisbol.model.TemporadaBeisbol;
import deportes.beisbol.service.EquipoService;
import deportes.beisbol.service.EtapaService;
import deportes.beisbol.utils.EtapaBeisbolAux;
import deportes.beisbol.web.model.TemporadaModel;

@Service
@Transactional(readOnly = true)
public class TemporadaServiceImpl implements TemporadaService {

	//private static final Logger logger = LoggerFactory.getLogger(TemporadaServiceImpl.class);
	
	@Autowired
	TemporadaRepository temporadaRepository;
	
	@Autowired
	LigaHistoricoRepository ligaHistoricoRepository;
	
	@Autowired
	EtapaService etapaService;
	
	@Autowired
	EquipoService equipoService;

	@Override
	public Optional<Temporada> findOneBd(short id) {
		return Optional.ofNullable(temporadaRepository.findOne(id));
	} 
	
	@Override
	@SuppressWarnings("unchecked")
	public TemporadaModel crearTemporadaModel(Short id, Optional<String> idioma) {
		TemporadaModel temporadaModelo = new TemporadaModel();
		
		Optional<TemporadaBeisbol> resultado = Optional.empty();
		
		Optional<Temporada> temporada = Optional.of(temporadaRepository.findOne(id)); 
		
		if (temporada.isPresent()) {
			resultado = Optional.ofNullable(TemporadaConverter.convierteDeBase(temporada.get()));
		}		
		
		if (resultado.isPresent()) {
			
			resultado.get().setEtapas(etapaService.findEtapasByTemporada(resultado.get(), idioma));
			
			Optional<EquipoBeisbol> campeon = equipoService.findCampeon(resultado.get(), idioma);
			
			if (campeon.isPresent()) {
				resultado.get().setCampeon(campeon.get());
			}
		}
		
		LinkedHashSet<EtapaBeisbolAux> etapaVista = new LinkedHashSet<>();
		Iterator<EtapaBeisbol> iteraEtapas = (Iterator<EtapaBeisbol>) resultado.get().getEtapas().iterator();
		EtapaBeisbol etapa;
		
		while (iteraEtapas.hasNext()) {
			etapa = iteraEtapas.next();
			
			etapaVista.add(EtapaConverter.convierteDeEntidad(etapa));
		}
		
		
		temporadaModelo.setTemporada(resultado.get());
		temporadaModelo.setEtapas(etapaVista);
		
		return temporadaModelo;
	}
	
	/* @Override
	public Optional<TemporadaBeisbol> findById(short id) {
		TemporadaBeisbol resultado = null;
		
		resultado = TemporadaConverter.convierteDeBase(temporadaRepository.findOne(id));
		
		return Optional.ofNullable(resultado);
	}

	@Override
	public Optional<TemporadaBeisbol> findByNombreAndLiga(
			String nombreTemporada, String ligaSiglas) {
		TemporadaBeisbol resultado = null;
		
		Optional<LigaHistorico> temporal = Optional.empty();

		Iterator<LigaHistorico> ligasHistorico = ligaHistoricoRepository
				.findBySiglas(ligaSiglas).iterator();

		while (ligasHistorico.hasNext()) {
			temporal = Optional.of(ligasHistorico.next());
		}
		
		if (temporal.isPresent()) {
			resultado = TemporadaConverter.convierteDeBase(temporadaRepository.findByNombreAndLigaHistorico
				(nombreTemporada, temporal.get()));
		}
		
		return Optional.ofNullable(resultado);
	}

	@Override
	public Optional<LigaBeisbol> findLigaPorTemporada(short id,	Optional<String> idioma) {
		Temporada resultado = null;
		
		resultado = temporadaRepository.findOne(id);
		
		// logger.info(resultado.getLigaHistorico().getSiglas());
		
		return Optional.ofNullable(LigaConverter.convierteDeBase(resultado.getLigaHistorico(), idioma));
	} */
}
