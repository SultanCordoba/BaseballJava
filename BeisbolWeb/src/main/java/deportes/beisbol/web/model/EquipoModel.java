package deportes.beisbol.web.model;

import java.util.LinkedHashSet;

import deportes.beisbol.jpa.model.Participante;
import deportes.beisbol.utils.EquipoAux;

public class EquipoModel {
	private LinkedHashSet<EquipoAux> equipos;
	private Participante participante;

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public LinkedHashSet<EquipoAux> getEquipos() {
		return equipos;
	}

	public void setEquipos(LinkedHashSet<EquipoAux> equipos) {
		this.equipos = equipos;
	}
}
