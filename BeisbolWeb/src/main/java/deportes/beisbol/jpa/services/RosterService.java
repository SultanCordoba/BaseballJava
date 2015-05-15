package deportes.beisbol.jpa.services;

import java.util.Collection;

import deportes.beisbol.jpa.model.Roster;
import deportes.beisbol.model.EquipoBeisbol;

public interface RosterService {
	public Collection<Roster> findRosterByEquipo(EquipoBeisbol equipo);
}
