package deportes.beisbol.utils;

import deportes.beisbol.model.JugadorBeisbol;

public class RosterBeisbol {
	private JugadorBeisbol jugador;
	private String posicion;
	private String otraTemporada;
	
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
