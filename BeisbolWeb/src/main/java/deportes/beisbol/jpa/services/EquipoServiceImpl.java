package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deportes.beisbol.converter.EquipoConverter;
import deportes.beisbol.jpa.model.Equipo;
import deportes.beisbol.jpa.repository.EquipoRepository;
import deportes.beisbol.model.EquipoBeisbol;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.TemporadaBeisbol;
import deportes.beisbol.service.EquipoService;

@Service
@Transactional(readOnly = true)
public class EquipoServiceImpl implements EquipoService {

	@Autowired
	EquipoRepository equipoRepository;
	
	@Override
	public Optional<EquipoBeisbol> findCampeon(TemporadaBeisbol t, Optional<String> idioma) {
		
		EquipoBeisbol equipoBeisbol = null;
		Equipo equipo = equipoRepository.findCampeon(t.getId());
		equipoBeisbol = EquipoConverter.convierteDeBase(equipo, idioma);
		
		return Optional.ofNullable(equipoBeisbol);
	}

	@Override
	public Optional<EquipoBeisbol> findOne(Short id, Optional<String> idioma) {
		
		EquipoBeisbol equipoBeisbol = null;
		Equipo equipo = equipoRepository.findOne(id);
		equipoBeisbol = EquipoConverter.convierteDeBase(equipo, idioma);
		
		return Optional.ofNullable(equipoBeisbol);
	}

	@Override
	public Collection<EquipoBeisbol> findByFranquicia(FranquiciaBeisbol arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
