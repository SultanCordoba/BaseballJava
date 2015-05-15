package deportes.beisbol.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import deportes.beisbol.model.EquipoBeisbol;
import deportes.beisbol.model.JugadorBeisbol;
import deportes.beisbol.web.controller.EquipoController;

public class EquipoAux {
	private static final Logger logger = LoggerFactory.getLogger(EquipoAux.class);
	
	private EquipoBeisbol equipoBeisbol;
	private LinkedHashSet<RecordEtapa> records;
	private int ganadosTotal;
	private int perdidosTotal;
	private boolean campeon;
	private LinkedHashSet<RosterBeisbol> managers;
	private LinkedHashSet<RosterBeisbol> pitchers;
	private LinkedHashSet<RosterBeisbol> bateadores;
	
	public EquipoAux() {
		managers = new LinkedHashSet<>();
		pitchers = new LinkedHashSet<>();
		bateadores = new LinkedHashSet<>();
	}
	
	public EquipoBeisbol getEquipoBeisbol() {
		return equipoBeisbol;
	}
	
	public void setEquipoBeisbol(EquipoBeisbol equipoBeisbol) {
		this.equipoBeisbol = equipoBeisbol;
	}
	
	public LinkedHashSet<RecordEtapa> getRecords() {
		return records;
	}
	
	public void setRecords(LinkedHashSet<RecordEtapa> records) {
		this.records = records;
		
		if (records.size() > 0) {
			Iterator<RecordEtapa> iteraRecord = records.iterator();
			RecordEtapa paso;
			ganadosTotal = 0;
			perdidosTotal = 0;
			campeon = false;
			
			while (iteraRecord.hasNext()) {
				paso = iteraRecord.next();
				
				ganadosTotal += paso.getGanados();
				perdidosTotal += paso.getPerdidos();
				
				if (paso.isCampeon()) {
					campeon = true;
				}
			}
		}
	}
	
	public String getTotalEquipo() {
		return "(" + String.valueOf(ganadosTotal) + " - " + String.valueOf(perdidosTotal) + ")";
	}
	
	public boolean isCampeon() {
		return campeon;
	}

	public LinkedHashSet<RosterBeisbol> getManagers() {
		return managers;
	}

	public void setManagers(LinkedHashSet<RosterBeisbol> managers) {
		this.managers = managers;
	}
	
	public void addManager(RosterBeisbol jugador) {		
		managers.add(jugador);
	}

	public LinkedHashSet<RosterBeisbol> getBateadores() {
		return bateadores;
	}

	public void setBateadores(LinkedHashSet<RosterBeisbol> bateadores) {
		this.bateadores = bateadores;
	}

	public void addBateador(RosterBeisbol jugador) {
		bateadores.add(jugador);
	}
	
	public LinkedHashSet<RosterBeisbol> getPitchers() {
		return pitchers;
	}

	public void setPitchers(LinkedHashSet<RosterBeisbol> pitchers) {
		this.pitchers = pitchers;
	}
	
	public void addPitcher(RosterBeisbol jugador) {
		pitchers.add(jugador);
	}
}
