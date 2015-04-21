package deportes.beisbol.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ronda database table.
 * 
 */
@Entity
@NamedQuery(name="Ronda.findAll", query="SELECT r FROM Ronda r")
public class Ronda implements Serializable {
	private static final long serialVersionUID = 1L;
	private byte id;
	private Timestamp fechaActualizacion;
	private String nombre;

	public Ronda() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
	}


	@Column(name="fecha_actualizacion")
	public Timestamp getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}