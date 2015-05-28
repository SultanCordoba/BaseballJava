package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deportes.beisbol.converter.FranquiciaConverter;
import deportes.beisbol.converter.LigaConverter;
import deportes.beisbol.jpa.model.Franquicia;
import deportes.beisbol.jpa.model.LigaHistorico;
import deportes.beisbol.jpa.repository.FranquiciaRepository;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.LigaBeisbol;

@Service
@Transactional(readOnly = true)
public class FranquiciaServiceImpl implements FranquiciaService {

	@Autowired
	FranquiciaRepository franquiciaRepository;
	
	@Override
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
	}

}
