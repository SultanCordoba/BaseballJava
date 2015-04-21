package deportes.beisbol.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the participante database table.
 * 
 */
@Entity
@NamedQuery(name="Participante.findAll", query="SELECT p FROM Participante p")
public class Participante implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private Timestamp fechaActualizacion;
	private byte invitado;
	private Set<Equipo> equipos;
	private Temporada temporada;
	private Set<Record> records;

	public Participante() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}


	@Column(name="fecha_actualizacion")
	public Timestamp getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}


	public byte getInvitado() {
		return this.invitado;
	}

	public void setInvitado(byte invitado) {
		this.invitado = invitado;
	}


	//bi-directional many-to-one association to Equipo
	@OneToMany(mappedBy="participante")
	@OrderBy("fechaInicio")
	public Set<Equipo> getEquipos() {
		return this.equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

	public Equipo addEquipo(Equipo equipo) {
		getEquipos().add(equipo);
		equipo.setParticipante(this);

		return equipo;
	}

	public Equipo removeEquipo(Equipo equipo) {
		getEquipos().remove(equipo);
		equipo.setParticipante(null);

		return equipo;
	}


	//bi-directional many-to-one association to Temporada
	@ManyToOne
	public Temporada getTemporada() {
		return this.temporada;
	}

	public void setTemporada(Temporada temporada) {
		this.temporada = temporada;
	}


	//bi-directional many-to-one association to Record
	@OneToMany(mappedBy="participante")
	public Set<Record> getRecords() {
		return this.records;
	}

	public void setRecords(Set<Record> records) {
		this.records = records;
	}

	public Record addRecord(Record record) {
		getRecords().add(record);
		record.setParticipante(this);

		return record;
	}

	public Record removeRecord(Record record) {
		getRecords().remove(record);
		record.setParticipante(null);

		return record;
	}

}