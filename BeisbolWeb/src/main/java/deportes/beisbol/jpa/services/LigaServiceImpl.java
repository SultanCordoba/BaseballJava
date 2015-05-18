package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import deportes.beisbol.converter.EquipoConverter;
import deportes.beisbol.converter.FranquiciaConverter;
import deportes.beisbol.converter.LigaConverter;
import deportes.beisbol.converter.TemporadaConverter;
import deportes.beisbol.jpa.repository.EquipoRepository;
import deportes.beisbol.jpa.repository.FranquiciaRepository;
import deportes.beisbol.jpa.repository.LigaHistoricoIntRepository;
import deportes.beisbol.jpa.repository.LigaHistoricoRepository;
import deportes.beisbol.jpa.repository.LigaRepository;
import deportes.beisbol.jpa.repository.TemporadaRepository;
import deportes.beisbol.jpa.model.Franquicia;
import deportes.beisbol.jpa.model.Liga;
import deportes.beisbol.jpa.model.LigaHistorico;
import deportes.beisbol.jpa.model.LigaHistoricoInt;
import deportes.beisbol.jpa.model.Temporada;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.model.TemporadaBeisbol;
import static deportes.beisbol.jpa.predicates.LigaPredicates.nombreIsLike;

@Service
@Transactional(readOnly = true)
public class LigaServiceImpl implements LigaService {

	private static final Logger logger = LoggerFactory.getLogger(LigaServiceImpl.class);
	
	@Autowired
	LigaRepository ligaRepository;

	@Autowired
	LigaHistoricoRepository ligaHistoricoRepository;
	
	@Autowired
	LigaHistoricoIntRepository ligaHistoricoIntRepository;
	
	@Autowired
	TemporadaRepository temporadaRepository;
	
	@Autowired
	EquipoRepository equipoRepository;
	
	@Autowired
	FranquiciaRepository franquiciaRepository;

	private LigaBeisbol completarLiga(LigaHistorico lh, Optional<String> idioma) {
		LigaBeisbol ligaBeisbol = null;
		
		ligaBeisbol = LigaConverter.convierteDeBase(lh, idioma);
		
		LinkedHashSet<TemporadaBeisbol> temporadas = new LinkedHashSet<>();
		
		Iterator<Temporada> temporadasBase = temporadaRepository
				.findByLigaHistorico(lh).iterator();
		TemporadaBeisbol tempPaso;
		
		while (temporadasBase.hasNext()) {
			tempPaso = TemporadaConverter.convierteDeBase(temporadasBase.next());
			tempPaso.setCampeon(EquipoConverter.convierteDeBase(equipoRepository.findCampeon(tempPaso.getId()), idioma));
			temporadas.add(tempPaso);
		}			
		
		ligaBeisbol.setTemporadas(temporadas);
		
		LinkedHashSet<FranquiciaBeisbol> franquicias = new LinkedHashSet<>();
		
		Iterator<Franquicia> franquiciasBase = franquiciaRepository
				.findLigaId(lh.getLiga().getId()).iterator();
		FranquiciaBeisbol franqPaso;
		
		while (franquiciasBase.hasNext()) {
			franqPaso = FranquiciaConverter.convierteDeBase(franquiciasBase.next(), idioma);
			if (!franqPaso.getNombre().isEmpty()) {
				franquicias.add(franqPaso);
			}
		}
		
		ligaBeisbol.setFranquicias(franquicias);
				
		return ligaBeisbol;
	}
	
	@Override
	public Optional<LigaBeisbol> findBySiglas(String siglas, Optional<String> idioma) {
		LigaBeisbol ligaBeisbol = null;
		Optional<LigaHistorico> temporal = Optional.empty();

		//ligasHistorico = ligaHistoricoRepository.findBySiglasEs(siglas).iterator();
		
		if (idioma.isPresent()) {
			String idiomaAbrev = Strings.nullToEmpty(idioma.get()).toUpperCase();
			if (!idiomaAbrev.equals("ES")) {
				
				Iterator<LigaHistoricoInt> ligasHistoricoInt = null;
				
				ligasHistoricoInt = ligaHistoricoIntRepository.buscarSiglasIdioma(siglas, idiomaAbrev).iterator();
				
				while (ligasHistoricoInt.hasNext()) {
					temporal = Optional.of(ligasHistoricoInt.next().getLigaHistorico());
				}
			}
			else {
				Iterator<LigaHistorico> ligasHistorico = null;
				
				ligasHistorico = ligaHistoricoRepository.findBySiglas(siglas).iterator();
				
				while (ligasHistorico.hasNext()) {
					temporal = Optional.of(ligasHistorico.next());
				} 
			}			
		}
						
		if (temporal.isPresent()) {
			ligaBeisbol = completarLiga(temporal.get(), idioma);
		}
				
		return Optional.ofNullable(ligaBeisbol);
	}

	@Override
	public Collection<LigaBeisbol> findLikeNombre(String nombre, Optional<String> idioma) {
		
		Collection<LigaBeisbol> resultado = new HashSet<LigaBeisbol>();
		
		Iterator<LigaHistorico> iteraLigas = ligaHistoricoRepository.findAll(nombreIsLike(nombre, 
				Strings.nullToEmpty(idioma.get()).toUpperCase())).iterator();
		
		while (iteraLigas.hasNext()) {
			resultado.add(LigaConverter.convierteDeBase(iteraLigas.next(), idioma));
		}
		
		return resultado;
	}

	@Override
	public Collection<LigaBeisbol> findActivas(Optional<String> idioma) {
		LinkedHashSet<LigaBeisbol> resultado = new LinkedHashSet<LigaBeisbol>();
		
		Iterator<LigaHistorico> iteraLigas = ligaHistoricoRepository.findLigasActivas().iterator();
		
		while (iteraLigas.hasNext()) {
			resultado.add(LigaConverter.convierteDeBase(iteraLigas.next(), idioma));
		}
		
		return resultado;
	}

	@Override
	public Collection<LigaBeisbol> findAll(Optional<String> idioma) {
		Collection<LigaBeisbol> resultado = new HashSet<LigaBeisbol>();
		
		Iterator<LigaHistorico> iteraLigas = ligaHistoricoRepository.findAll().iterator();
		
		while (iteraLigas.hasNext()) {
			resultado.add(LigaConverter.convierteDeBase(iteraLigas.next(), idioma));
		}
		
		return resultado;
	}

	@Override
	public Optional<LigaBeisbol> findOne(Byte id, Optional<String> idioma) {
		LigaBeisbol ligaBeisbol = null;
		Optional<LigaHistorico> temporal = Optional.empty();
		
		if (idioma.isPresent()) {
			String idiomaAbrev = Strings.nullToEmpty(idioma.get()).toUpperCase();
			if (!idiomaAbrev.equals("ES")) {
				
				Iterator<LigaHistoricoInt> ligasHistoricoInt = null;
				
				ligasHistoricoInt = ligaHistoricoIntRepository.buscarLigaId(id, idiomaAbrev).iterator();
				
				while (ligasHistoricoInt.hasNext()) {
					temporal = Optional.of(ligasHistoricoInt.next().getLigaHistorico());
				}
			}
			else {
				Iterator<LigaHistorico> ligasHistorico = null;
				
				ligasHistorico = ligaHistoricoRepository.buscarLigaId(id).iterator();
				
				while (ligasHistorico.hasNext()) {
					temporal = Optional.of(ligasHistorico.next());
				} 
			}			
		}
						
		if (temporal.isPresent()) {
			ligaBeisbol = completarLiga(temporal.get(), idioma);
		}
				
		return Optional.ofNullable(ligaBeisbol);

	}

	@Override
	public Optional<Liga> findOneBd(Byte id) {
		return Optional.ofNullable(ligaRepository.findOne(id));
	}

}
