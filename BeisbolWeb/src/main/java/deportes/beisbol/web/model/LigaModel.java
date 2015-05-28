package deportes.beisbol.web.model;

import java.util.Collection;

import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.utils.EquipoPais;

public class LigaModel {
	private LigaBeisbol liga;
	private Collection<EquipoPais> equipos;
	
	public LigaBeisbol getLiga() {
		return liga;
	}
	
	public void setLiga(LigaBeisbol liga) {
		this.liga = liga;
	}
	
	public Collection<EquipoPais> getEquipos() {
		return equipos;
	}
	
	public void setEquipos(Collection<EquipoPais> equipos) {
		this.equipos = equipos;
	}
}
