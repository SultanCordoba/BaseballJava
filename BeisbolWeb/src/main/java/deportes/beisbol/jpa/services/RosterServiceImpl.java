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
		
		// return rosterRepository.findByEquipoIdOrderByFechaInicioAsc(equipo.getId());
		
		/* LinkedHashSet<Roster> resultado = new LinkedHashSet<>();
		
		Iterator<Roster> iteraRoster = rosterRepository.findByEquipoIdOrderByFechaInicioAsc(equipo.getId()).iterator();
		JugadorBeisbol jugador;
		Jugador jugadorBase;
		Roster rosterPaso;
		
		while (iteraRoster.hasNext()) {
			rosterPaso = iteraRoster.next();
			jugadorBase = jugadorRepository.findOne(rosterPaso.getJugadorId());
			jugador = JugadorConverter.convierteDeBase(jugadorBase);
			
			if (Strings.isNullOrEmpty(rosterPaso.getPosicion())) {
				if (!Strings.isNullOrEmpty(jugadorBase.getPosicion())) {
					jugador.setPosicion(jugadorBase.getPosicion());
				}
			}
			else {
				jugador.setPosicion(rosterPaso.getPosicion());
			}
			
			resultado.add(jugador);			
		}
		
		// TODO Auto-generated method stub
		return resultado; */
	}

	@Override
	public Collection<Roster> hallarRosterByJugadorTemporada(Short jugadorId,
			Date fechaInicio, Date fechaFin) {
		
		//return rosterRepository.hallarRosterByJugadorAndTemporada(jugadorId, fechaInicio, fechaFin);
		
		return (Collection<Roster>) rosterRepository.findAll
				(RosterPredicates.rosterJugadorFechas(jugadorId, fechaInicio, fechaFin), 
				 RosterPredicates.orderByFechaInicioAsc());
	}

}
