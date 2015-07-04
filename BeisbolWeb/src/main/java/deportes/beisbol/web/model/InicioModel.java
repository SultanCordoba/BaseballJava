package deportes.beisbol.web.model;

import java.util.LinkedHashSet;

public class InicioModel {
	private LinkedHashSet<TemporadaActual> temporadasEnCurso;

	public LinkedHashSet<TemporadaActual> getTemporadasEnCurso() {
		return temporadasEnCurso;
	}

	public void setTemporadasEnCurso(
			LinkedHashSet<TemporadaActual> temporadasEnCurso) {
		this.temporadasEnCurso = temporadasEnCurso;
	}
}
