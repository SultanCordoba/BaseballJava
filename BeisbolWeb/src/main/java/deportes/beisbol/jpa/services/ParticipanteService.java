package deportes.beisbol.jpa.services;

import java.util.Optional;

import deportes.beisbol.jpa.model.Participante;

public interface ParticipanteService {
	public Optional<Participante> findOne(Short id);
}
