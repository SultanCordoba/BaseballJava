package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import deportes.beisbol.converter.JugadorConverter;
import deportes.beisbol.jpa.model.Jugador;
import deportes.beisbol.jpa.model.Roster;
import deportes.beisbol.jpa.repository.JugadorRepository;
import deportes.beisbol.jpa.repository.RosterRepository;
import deportes.beisbol.model.EquipoBeisbol;
import deportes.beisbol.model.JugadorBeisbol;

@Service
@Transactional(readOnly = true)
public class RosterServiceImpl implements RosterService {

	@Autowired
	RosterRepository rosterRepository;
	
	@Autowired
	JugadorRepository jugadorRepository;
	
	@Override
	public Collection<Roster> findRosterByEquipo(EquipoBeisbol equipo) {
		
		return rosterRepository.findByEquipoIdOrderByFechaInicioAsc(equipo.getId());
		
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

}
