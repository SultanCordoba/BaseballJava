package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Optional;




/* import org.slf4j.Logger;
import org.slf4j.LoggerFactory; */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import deportes.beisbol.converter.EquipoConverter;
import deportes.beisbol.converter.FranquiciaConverter;
import deportes.beisbol.converter.LigaConverter;
import deportes.beisbol.converter.TemporadaConverter;
import deportes.beisbol.jpa.model.Franquicia;
import deportes.beisbol.jpa.model.Liga;
import deportes.beisbol.jpa.model.LigaHistorico;
import deportes.beisbol.jpa.model.LigaHistoricoInt;
import deportes.beisbol.jpa.model.Temporada;
import deportes.beisbol.jpa.predicates.EquipoPredicates;
import deportes.beisbol.jpa.predicates.FranquiciaPredicates;
import deportes.beisbol.jpa.predicates.LigaPredicates;
import deportes.beisbol.jpa.predicates.TemporadaPredicates;
import deportes.beisbol.jpa.repository.EquipoRepository;
import deportes.beisbol.jpa.repository.FranquiciaRepository;
import deportes.beisbol.jpa.repository.LigaHistoricoIntRepository;
import deportes.beisbol.jpa.repository.LigaHistoricoRepository;
import deportes.beisbol.jpa.repository.LigaRepository;
import deportes.beisbol.jpa.repository.TemporadaRepository;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.model.RangoFechaBeisbol;
import deportes.beisbol.model.TemporadaBeisbol;
import deportes.beisbol.utils.EquipoPais;
import deportes.beisbol.web.model.LigaModel;

@Service
@Transactional(readOnly = true)
public class LigaServiceImpl implements LigaService {

	// private static final Logger logger = LoggerFactory.getLogger(LigaServiceImpl.class);
	
	@Autowired
	LigaRepository ligaRepository;
	
	@Autowired
	TemporadaRepository temporadaRepository;
	
	@Autowired
	EquipoRepository equipoRepository;
	
	@Autowired
	FranquiciaRepository franquiciaRepository;

	@Autowired
	LigaHistoricoRepository ligaHistoricoRepository;
	
	@Autowired
	LigaHistoricoIntRepository ligaHistoricoIntRepository;

	private LigaBeisbol completarLiga(LigaHistorico lh, Optional<String> idioma) {
		LigaBeisbol ligaBeisbol = null;
		
		ligaBeisbol = LigaConverter.convierteDeBase(lh, idioma);
		
		LinkedHashSet<TemporadaBeisbol> temporadas = new LinkedHashSet<>();
		
		/* Iterator<Temporada> temporadasBase = temporadaRepository
				.findByLigaHistorico(lh).iterator(); */
		
		Iterator<Temporada> temporadasBase = temporadaRepository.findAll
				(TemporadaPredicates.ligaHistoricoIs(lh)).iterator();
				
		
		TemporadaBeisbol tempPaso;
		
		while (temporadasBase.hasNext()) {
			tempPaso = TemporadaConverter.convierteDeBase(temporadasBase.next());
			// tempPaso.setCampeon(EquipoConverter.convierteDeBase(equipoRepository.findCampeon(tempPaso.getId()), idioma));
			
			tempPaso.setCampeon(EquipoConverter.convierteDeBase(
					equipoRepository.findOne(EquipoPredicates.campeonTemporada(tempPaso.getId())), idioma));
			temporadas.add(tempPaso);
		}			
		
		ligaBeisbol.setTemporadas(temporadas);
		
		LinkedHashSet<FranquiciaBeisbol> franquicias = new LinkedHashSet<>();
		
		/* Iterator<Franquicia> franquiciasBase = franquiciaRepository
				.findLigaId(lh.getLiga().getId()).iterator(); */
		Iterator<Franquicia> franquiciasBase = franquiciaRepository.findAll
				(FranquiciaPredicates.isLigaId(lh.getLiga().getId())).iterator();
		
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
	public Optional<Liga> findOneBd(Byte id) {
		return Optional.ofNullable(ligaRepository.findOne(id));
	}
	
	@Override
	public Collection<LigaBeisbol> getAllLigas(Optional<String> idioma) {
		LinkedHashSet<LigaBeisbol> resultado = new LinkedHashSet<LigaBeisbol>();
		
		//Iterator<LigaHistorico> iteraLigas = ligaHistoricoRepository.findLigasActivas().iterator();
		Iterator<LigaHistorico> iteraLigas = ligaHistoricoRepository.findAll
				(LigaPredicates.ligasActivas(), LigaPredicates.orderByNombreAsc()).iterator();
		
		while (iteraLigas.hasNext()) {
			resultado.add(LigaConverter.convierteDeBase(iteraLigas.next(), idioma));
		}
		
		return resultado;
	}
    
	@Override
	@SuppressWarnings("unchecked")
	public LigaModel creaLigaModel(Byte id, Optional<String> idioma) {
		LigaModel resultado = new LigaModel();
		
        Optional<LigaBeisbol> ligaBeisbol = null;
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
				
				//ligasHistorico = ligaHistoricoRepository.buscarLigaId(id).iterator();
				ligasHistorico = ligaHistoricoRepository.findAll(LigaPredicates.idIs(id)).iterator();
				
				while (ligasHistorico.hasNext()) {
					temporal = Optional.of(ligasHistorico.next());
				} 
			}			
		}
						
		if (temporal.isPresent()) {
			ligaBeisbol = Optional.of(completarLiga(temporal.get(), idioma));
		}
                
		//ligaBeisbol = ligaService.findOne(id, idioma);		
		//resultado.orElseThrow(() -> new LigaNotFoundException(id));
		
		Iterator<FranquiciaBeisbol> iteradorFranquicias = (Iterator<FranquiciaBeisbol>) 
				ligaBeisbol.get().getFranquicias().iterator();
		FranquiciaBeisbol franquicia = null;
		
		LinkedHashMap<String, EquipoPais> equipos = new LinkedHashMap<>();
		
		EquipoPais equipoAux = null;
		
		while (iteradorFranquicias.hasNext()) {
			franquicia = iteradorFranquicias.next();
			Iterator<RangoFechaBeisbol> nombres = (Iterator<RangoFechaBeisbol>) 
					franquicia.getNombres().iterator();
			
			while (nombres.hasNext()) {
				equipoAux = new EquipoPais();
				equipoAux.setIdFranquicia(franquicia.getId());
				equipoAux.setNombre(nombres.next().getNombre());
				
				equipoAux.setPais(franquicia.getPais());
				equipos.put(equipoAux.getNombre(), equipoAux);
			}
			
		}
				
		resultado.setLiga(ligaBeisbol.get());
		resultado.setEquipos(equipos.values());
		
		return resultado;
	}
}
