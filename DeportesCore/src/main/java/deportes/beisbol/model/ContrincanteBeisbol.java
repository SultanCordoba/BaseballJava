package deportes.beisbol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import deportes.core.interfaces.ContrincanteInterfaz;
import deportes.core.interfaces.EquipoInterfaz;

@JsonTypeName("contBeisbol")
public class ContrincanteBeisbol implements ContrincanteInterfaz {

	private EquipoBeisbol equipo;
	private short score;
	private byte hits;
	private byte errores;
	
	@JsonIgnore
	@Override
	public String getNombre() {		
		return equipo.getNombre();
	}

	@JsonIgnore
	@Override
	public void setNombre(String nombre) {
		throw new UnsupportedOperationException("Operación no soportada.");
	}

	@Override
	public EquipoBeisbol getEquipo() {
		return equipo;
	}

	@Override
	public void setEquipo(EquipoInterfaz equipo) {
		this.equipo = (EquipoBeisbol) equipo;
	}

	@Override
	public short getScore() {
		return score;
	}

	@Override
	public void setScore(short score) {
		this.score = score;
	}

	public byte getHits() {
		return hits;
	}

	public void setHits(byte hits) {
		this.hits = hits;
	}

	public byte getErrores() {
		return errores;
	}

	public void setErrores(byte errores) {
		this.errores = errores;
	}
	
	public ContrincanteBeisbol() {}

}
