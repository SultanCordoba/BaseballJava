package deportes.beisbol.jpa.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the etapa database table.
 * 
 */
@Entity
@NamedQuery(name="Etapa.findAll", query="SELECT e FROM Etapa e")
public class Etapa implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private Timestamp fechaActualizacion;
	private String nombre;
	private byte ordenRonda;
	private short previoId;
	private byte rondaId;
	private Temporada temporada;
	private Set<Partido> partidos;
	private Set<Record> records;
	private Set<EtapaInt> etapaInts;

	public Etapa() {
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

	@Column(name="nombre")
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Column(name="orden_ronda")
	public byte getOrdenRonda() {
		return this.ordenRonda;
	}

	public void setOrdenRonda(byte ordenRonda) {
		this.ordenRonda = ordenRonda;
	}


	@Column(name="previo_id")
	public short getPrevioId() {
		return this.previoId;
	}

	public void setPrevioId(short previoId) {
		this.previoId = previoId;
	}


	@Column(name="ronda_id")
	public byte getRondaId() {
		return this.rondaId;
	}

	public void setRondaId(byte rondaId) {
		this.rondaId = rondaId;
	}


	//bi-directional many-to-one association to Temporada
	@ManyToOne
	public Temporada getTemporada() {
		return this.temporada;
	}

	public void setTemporada(Temporada temporada) {
		this.temporada = temporada;
	}


	//bi-directional many-to-one association to Partido
	@OneToMany(mappedBy="etapa")
	public Set<Partido> getPartidos() {
		return this.partidos;
	}

	public void setPartidos(Set<Partido> partidos) {
		this.partidos = partidos;
	}

	public Partido addPartido(Partido partido) {
		getPartidos().add(partido);
		partido.setEtapa(this);

		return partido;
	}

	public Partido removePartido(Partido partido) {
		getPartidos().remove(partido);
		partido.setEtapa(null);

		return partido;
	}


	//bi-directional many-to-one association to Record
	@OneToMany(mappedBy="etapa", fetch=FetchType.EAGER)
	@OrderBy("nombreGrupo")
	public Set<Record> getRecords() {
		return this.records;
	}

	public void setRecords(Set<Record> records) {
		this.records = records;
	}

	public Record addRecord(Record record) {
		getRecords().add(record);
		record.setEtapa(this);

		return record;
	}

	public Record removeRecord(Record record) {
		getRecords().remove(record);
		record.setEtapa(null);

		return record;
	}

	
	//bi-directional many-to-one association to Etapa
	@OneToMany(mappedBy="etapa", fetch=FetchType.EAGER)
	public Set<EtapaInt> getEtapaInts() {
		return this.etapaInts;
	}

	public void setEtapaInts(Set<EtapaInt> etapaInts) {
		this.etapaInts = etapaInts;
	}

	public EtapaInt addEtapaInt(EtapaInt etapaInt) {
		getEtapaInts().add(etapaInt);
		etapaInt.setEtapa(this);
		
		return etapaInt;
	}

	public EtapaInt removeEtapaInt(EtapaInt etapaInt) {
		getEtapaInts().remove(etapaInt);
		etapaInt.setEtapa(null);

		return etapaInt;
	}
}