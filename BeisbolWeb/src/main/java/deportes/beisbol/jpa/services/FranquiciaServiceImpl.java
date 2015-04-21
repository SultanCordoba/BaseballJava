package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deportes.beisbol.converter.FranquiciaConverter;
import deportes.beisbol.jpa.repository.FranquiciaRepository;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.service.FranquiciaService;

@Service
@Transactional(readOnly = true)
public class FranquiciaServiceImpl implements FranquiciaService {

	@Autowired
	FranquiciaRepository franquiciaRepository;
	
	@Override
	public Optional<FranquiciaBeisbol> findById(short id) {
		
		FranquiciaBeisbol resultado = null;
		
		resultado = FranquiciaConverter.convierteDeBase(franquiciaRepository.findOne(id));
		
		return Optional.ofNullable(resultado);
	}

	@Override
	public Collection<FranquiciaBeisbol> findByLigaId(Byte id) {
		// TODO Auto-generated method stub
		return null;
	}

}
