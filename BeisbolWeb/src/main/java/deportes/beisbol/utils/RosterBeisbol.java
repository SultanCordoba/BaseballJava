package deportes.beisbol.utils;

import deportes.beisbol.model.JugadorBeisbol;

public class RosterBeisbol {
	private JugadorBeisbol jugador;
	private String posicion;
	private String otraTemporada;
	private String equipo;
	private String temporada;
	private String liga;
	
	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public String getLiga() {
		return liga;
	}

	public void setLiga(String liga) {
		this.liga = liga;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public JugadorBeisbol getJugador() {
		return jugador;
	}
	
	public void setJugador(JugadorBeisbol jugador) {
		this.jugador = jugador;
	}
	
	public String getPosicion() {
		return posicion;
	}
	
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	
	public String getOtraTemporada() {
		return otraTemporada;
	}
	
	public void setOtraTemporada(String otraTemporada) {
		this.otraTemporada = otraTemporada;
	}
	
	
}
