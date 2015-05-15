package deportes.beisbol.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import deportes.core.interfaces.JugadorInterfaz;

public class JugadorBeisbol implements JugadorInterfaz {

	public short id;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private LocalDate fechaNacimiento;
	private String apodo;
	private String posicion;
	private String batea;
	private String tira;

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
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
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	@Override
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	@Override
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	@Override
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public String getBatea() {
		return batea;
	}

	public void setBatea(String batea) {
		this.batea = batea;
	}

	public String getTira() {
		return tira;
	}

	public void setTira(String tira) {
		this.tira = tira;
	}

	@Override
	public String getNombreCompleto() {
		Joiner joiner = Joiner.on(" ").skipNulls();
		return joiner.join(nombre, apellidoPaterno, apellidoMaterno);
	}

	@Override
	public String getNombreAbreviado() {
		Joiner joiner = Joiner.on(" ").skipNulls();
		Splitter splitter = Splitter.on(" ").trimResults().omitEmptyStrings();
		
		ArrayList<String> separaNombre = Lists.newArrayList(splitter.split(nombre));
		
		ArrayList<String> armaNombre = new ArrayList<>();
		
		armaNombre.add(separaNombre.get(0));
		
		if (separaNombre.size() > 1) {
			armaNombre.add(separaNombre.get(1).substring(0,1) + ".");
		}
		
		armaNombre.add(apellidoPaterno);
		
		return joiner.join(armaNombre);
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}

		if (getClass() != object.getClass()) {
			return false;
		}

		final JugadorBeisbol other = (JugadorBeisbol) object;
		return Objects.equals(this.nombre, other.nombre) &&
				Objects.equals(this.apellidoPaterno, other.apellidoPaterno) &&
				Objects.equals(this.apellidoMaterno, other.apellidoMaterno);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.nombre, this.apellidoPaterno, this.apellidoMaterno);
	}

}
