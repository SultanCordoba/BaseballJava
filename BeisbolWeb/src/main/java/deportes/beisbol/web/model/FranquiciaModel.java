package deportes.beisbol.web.model;

import java.util.LinkedHashSet;

import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.utils.RecordEtapa;

public class FranquiciaModel {
	
	private FranquiciaBeisbol franquicia;
	private LigaBeisbol liga;
	private LinkedHashSet<RecordEtapa> temporadas;
	private String pais;
	
	public FranquiciaBeisbol getFranquicia() {
		return franquicia;
	}
	
	public void setFranquicia(FranquiciaBeisbol franquicia) {
		this.franquicia = franquicia;
	}
	
	public LigaBeisbol getLiga() {
		return liga;
	}
	
	public void setLiga(LigaBeisbol liga) {
		this.liga = liga;
	}
	
	public LinkedHashSet<RecordEtapa> getTemporadas() {
		return temporadas;
	}
	
	public void setTemporadas(LinkedHashSet<RecordEtapa> temporadas) {
		this.temporadas = temporadas;
	}
	
	public String getPais() {
		return pais;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
}
