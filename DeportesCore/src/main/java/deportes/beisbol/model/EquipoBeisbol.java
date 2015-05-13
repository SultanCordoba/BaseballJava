package deportes.beisbol.model;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonTypeName;

import deportes.core.interfaces.DeporteEquipoInterfaz;
import deportes.core.interfaces.DeporteJugadorInterfaz;

@JsonTypeName("equipoBeisbol")
public class EquipoBeisbol implements DeporteEquipoInterfaz {

	private Short id;
	private String siglas;
	private String nombre;
	private String nombreTabla;
	private String logotipo;
	private String parque;
	
	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	@Override
	public String getSiglas() {
		return siglas;
	}

	@Override
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String getNombreTabla() {
		return nombreTabla;
	}

	@Override
	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	@Override
	public Collection<? extends DeporteJugadorInterfaz> getJugadores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setJugadores(
			Collection<? extends DeporteJugadorInterfaz> jugadores) {
		// TODO Auto-generated method stub

	}
	
	public String getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(String logotipo) {
		this.logotipo = logotipo;
	}

	public String getParque() {
		return parque;
	}

	public void setParque(String parque) {
		this.parque = parque;
	}

	public EquipoBeisbol() {}

}
