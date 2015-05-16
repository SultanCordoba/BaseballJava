package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Date;

import deportes.beisbol.jpa.model.Roster;
import deportes.beisbol.model.EquipoBeisbol;

public interface RosterService {
	public Collection<Roster> findRosterByEquipo(EquipoBeisbol equipo);
	
	public Collection<Roster> hallarRosterByJugadorTemporada(Short jugadorId, Date fechaInicio, Date fechaFin);
}
