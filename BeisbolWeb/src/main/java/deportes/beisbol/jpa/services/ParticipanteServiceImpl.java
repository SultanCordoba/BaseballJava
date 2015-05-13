package deportes.beisbol.jpa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deportes.beisbol.jpa.model.Participante;
import deportes.beisbol.jpa.repository.ParticipanteRepository;

@Service
@Transactional(readOnly = true)
public class ParticipanteServiceImpl implements ParticipanteService {

	@Autowired
	ParticipanteRepository participanteRepository;
	
	@Override
	public Optional<Participante> findOne(Short id) {
		return Optional.ofNullable(participanteRepository.findOne(id));
	}

}
