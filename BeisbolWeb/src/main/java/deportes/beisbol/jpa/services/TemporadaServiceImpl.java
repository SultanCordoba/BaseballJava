package deportes.beisbol.jpa.services;

import java.util.Iterator;
import java.util.Optional;

/* import org.slf4j.Logger;
import org.slf4j.LoggerFactory; */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deportes.beisbol.converter.LigaConverter;
import deportes.beisbol.converter.TemporadaConverter;
import deportes.beisbol.jpa.model.LigaHistorico;
import deportes.beisbol.jpa.model.Temporada;
import deportes.beisbol.jpa.repository.LigaHistoricoRepository;
import deportes.beisbol.jpa.repository.TemporadaRepository;
import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.model.TemporadaBeisbol;

@Service
@Transactional(readOnly = true)
public class TemporadaServiceImpl implements TemporadaService {

	//private static final Logger logger = LoggerFactory.getLogger(TemporadaServiceImpl.class);
	
	@Autowired
	TemporadaRepository temporadaRepository;
	
	@Autowired
	LigaHistoricoRepository ligaHistoricoRepository;
	
	@Override
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
	}

	@Override
	public Optional<Temporada> findOneBd(short id) {
		return Optional.ofNullable(temporadaRepository.findOne(id));
	}
}
