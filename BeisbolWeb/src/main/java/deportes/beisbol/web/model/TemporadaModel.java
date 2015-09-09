package deportes.beisbol.web.model;

import java.util.LinkedHashSet;

import deportes.beisbol.model.PartidoBeisbol;
import deportes.beisbol.model.TemporadaBeisbol;
import deportes.beisbol.utils.EtapaBeisbolAux;

public class TemporadaModel {
	private TemporadaBeisbol temporada;
	private LinkedHashSet<EtapaBeisbolAux> etapas;
	private LinkedHashSet<PartidoBeisbol> partidos;

	public LinkedHashSet<PartidoBeisbol> getPartidos() {
		return partidos;
	}

	public void setPartidos(LinkedHashSet<PartidoBeisbol> partidos) {
		this.partidos = partidos;
	}

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
