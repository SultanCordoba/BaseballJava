package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deportes.beisbol.jpa.model.Roster;
import deportes.beisbol.jpa.predicates.RosterPredicates;
import deportes.beisbol.jpa.repository.JugadorRepository;
import deportes.beisbol.jpa.repository.RosterRepository;
import deportes.beisbol.model.EquipoBeisbol;

@Service
@Transactional(readOnly = true)
public class RosterServiceImpl implements RosterService {

	@Autowired
	RosterRepository rosterRepository;
	
	@Autowired
	JugadorRepository jugadorRepository;
	
	@Override
	public Collection<Roster> findRosterByEquipo(EquipoBeisbol equipo) {
		
		return (Collection<Roster>) rosterRepository.findAll(RosterPredicates.rosterEquipo(equipo.getId()), 
				RosterPredicates.orderByFechaInicioAsc());
	}

	@Override
	public Collection<Roster> hallarRosterByJugadorTemporada(Short jugadorId,
			Date fechaInicio, Date fechaFin) {

		return (Collection<Roster>) rosterRepository.findAll
				(RosterPredicates.rosterJugadorFechas(jugadorId, fechaInicio, fechaFin), 
				 RosterPredicates.orderByFechaInicioAsc());
	}
}
