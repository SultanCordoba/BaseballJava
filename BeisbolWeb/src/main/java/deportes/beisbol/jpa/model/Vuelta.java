package deportes.beisbol.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the vuelta database table.
 * 
 */
@Entity
@NamedQuery(name="Vuelta.findAll", query="SELECT v FROM Vuelta v")
public class Vuelta implements Serializable {
	private static final long serialVersionUID = 1L;
	private byte id;
	private Timestamp fechaActualizacion;
	private String nombre;
	private Set<Partido> partidos;
	private Set<Record> records;

	public Vuelta() {
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


	//bi-directional many-to-one association to Partido
	@OneToMany(mappedBy="vuelta")
	public Set<Partido> getPartidos() {
		return this.partidos;
	}

	public void setPartidos(Set<Partido> partidos) {
		this.partidos = partidos;
	}

	public Partido addPartido(Partido partido) {
		getPartidos().add(partido);
		partido.setVuelta(this);

		return partido;
	}

	public Partido removePartido(Partido partido) {
		getPartidos().remove(partido);
		partido.setVuelta(null);

		return partido;
	}


	//bi-directional many-to-one association to Record
	@OneToMany(mappedBy="vuelta")
	public Set<Record> getRecords() {
		return this.records;
	}

	public void setRecords(Set<Record> records) {
		this.records = records;
	}

	public Record addRecord(Record record) {
		getRecords().add(record);
		record.setVuelta(this);

		return record;
	}

	public Record removeRecord(Record record) {
		getRecords().remove(record);
		record.setVuelta(null);

		return record;
	}

}