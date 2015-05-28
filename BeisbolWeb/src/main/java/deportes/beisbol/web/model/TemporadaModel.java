package deportes.beisbol.web.model;

import java.util.LinkedHashSet;

import deportes.beisbol.model.TemporadaBeisbol;
import deportes.beisbol.utils.EtapaBeisbolAux;

public class TemporadaModel {
	private TemporadaBeisbol temporada;
	private LinkedHashSet<EtapaBeisbolAux> etapas;
	
	public TemporadaBeisbol getTemporada() {
		return temporada;
	}
	
	public void setTemporada(TemporadaBeisbol temporada) {
		this.temporada = temporada;
	}
	
	public LinkedHashSet<EtapaBeisbolAux> getEtapas() {
		return etapas;
	}
	
	public void setEtapas(LinkedHashSet<EtapaBeisbolAux> etapas) {
		this.etapas = etapas;
	}
}
