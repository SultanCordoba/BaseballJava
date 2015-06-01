package deportes.beisbol.web.model;

import java.util.LinkedHashSet;

import deportes.beisbol.model.JugadorBeisbol;
import deportes.beisbol.utils.RosterBeisbol;

public class JugadorModel {
	private JugadorBeisbol jugador;
	private LinkedHashSet<RosterBeisbol> rosters;
	private LinkedHashSet<RosterBeisbol> managers;

	public LinkedHashSet<RosterBeisbol> getManagers() {
		return managers;
	}

	public void setManagers(LinkedHashSet<RosterBeisbol> managers) {
		this.managers = managers;
	}

	public LinkedHashSet<RosterBeisbol> getRosters() {
		return rosters;
	}

	public void setRosters(LinkedHashSet<RosterBeisbol> rosters) {
		this.rosters = rosters;
	}

	public JugadorBeisbol getJugador() {
		return jugador;
	}

	public void setJugador(JugadorBeisbol jugador) {
		this.jugador = jugador;
	}
}
