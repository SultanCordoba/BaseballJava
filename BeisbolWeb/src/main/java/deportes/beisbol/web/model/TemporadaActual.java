package deportes.beisbol.web.model;

import deportes.beisbol.model.TemporadaBeisbol;

public class TemporadaActual {
	private TemporadaBeisbol temporada;
	private String liga;
	
	public TemporadaBeisbol getTemporada() {
		return temporada;
	}
	
	public void setTemporada(TemporadaBeisbol temporada) {
		this.temporada = temporada;
	}
	
	public String getLiga() {
		return liga;
	}
	
	public void setLiga(String liga) {
		this.liga = liga;
	}
}
