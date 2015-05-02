package deportes.beisbol.jpa.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the record database table.
 * 
 */
@Entity
// @NamedQuery(name="Record.findAll", query="SELECT r FROM Record r")
public class Record implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private Timestamp fechaActualizacion;
	private byte ganados;
	private String nombreGrupo;
	private byte perdidos;
	private Etapa etapa;
	private Participante participante;
	private Vuelta vuelta;
	private Set<RecordInt> recordInts;

	public Record() {
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


	public byte getGanados() {
		return this.ganados;
	}

	public void setGanados(byte ganados) {
		this.ganados = ganados;
	}

	@Column(name="nombre_grupo")
	public String getNombreGrupo() {
		return this.nombreGrupo;
	}

	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}


	public byte getPerdidos() {
		return this.perdidos;
	}

	public void setPerdidos(byte perdidos) {
		this.perdidos = perdidos;
	}


	//bi-directional many-to-one association to Etapa
	@ManyToOne
	public Etapa getEtapa() {
		return this.etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}


	//bi-directional many-to-one association to Participante
	@ManyToOne
	public Participante getParticipante() {
		return this.participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}


	//bi-directional many-to-one association to Vuelta
	@ManyToOne
	public Vuelta getVuelta() {
		return this.vuelta;
	}

	public void setVuelta(Vuelta vuelta) {
		this.vuelta = vuelta;
	}

	//bi-directional many-to-one association to Etapa
	@OneToMany(mappedBy="record", fetch=FetchType.EAGER)
	public Set<RecordInt> getRecordInts() {
		return this.recordInts;
	}

	public void setRecordInts(Set<RecordInt> recordInts) {
		this.recordInts = recordInts;
	}

	public RecordInt addRecordInt(RecordInt recordInt) {
		getRecordInts().add(recordInt);
		recordInt.setRecord(this);
		
		return recordInt;
	}

	public RecordInt removeRecordInt(RecordInt recordInt) {
		getRecordInts().remove(recordInt);
		recordInt.setRecord(null);

		return recordInt;
	}
}