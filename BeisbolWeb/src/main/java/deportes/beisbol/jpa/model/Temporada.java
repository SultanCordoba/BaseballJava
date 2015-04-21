package deportes.beisbol.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the temporada database table.
 * 
 */
@Entity
@NamedQuery(name="Temporada.findAll", query="SELECT t FROM Temporada t")
public class Temporada implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private Timestamp fechaActualizacion;
	private Date fechaFin;
	private Date fechaInicio;
	private String nombre;
	private byte tempCompleta;
	private int tipoPlayOff;
	private Set<Etapa> etapas;
	private Set<Participante> participantes;
	private LigaHistorico ligaHistorico;

	public Temporada() {
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


	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin")
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Column(name="temp_completa")
	public byte getTempCompleta() {
		return this.tempCompleta;
	}

	public void setTempCompleta(byte tempCompleta) {
		this.tempCompleta = tempCompleta;
	}


	@Column(name="tipo_play_off")
	public int getTipoPlayOff() {
		return this.tipoPlayOff;
	}

	public void setTipoPlayOff(int tipoPlayOff) {
		this.tipoPlayOff = tipoPlayOff;
	}


	//bi-directional many-to-one association to Etapa
	@OneToMany(mappedBy="temporada")
	public Set<Etapa> getEtapas() {
		return this.etapas;
	}

	public void setEtapas(Set<Etapa> etapas) {
		this.etapas = etapas;
	}

	public Etapa addEtapa(Etapa etapa) {
		getEtapas().add(etapa);
		etapa.setTemporada(this);

		return etapa;
	}

	public Etapa removeEtapa(Etapa etapa) {
		getEtapas().remove(etapa);
		etapa.setTemporada(null);

		return etapa;
	}


	//bi-directional many-to-one association to Participante
	@OneToMany(mappedBy="temporada")
	public Set<Participante> getParticipantes() {
		return this.participantes;
	}

	public void setParticipantes(Set<Participante> participantes) {
		this.participantes = participantes;
	}

	public Participante addParticipante(Participante participante) {
		getParticipantes().add(participante);
		participante.setTemporada(this);

		return participante;
	}

	public Participante removeParticipante(Participante participante) {
		getParticipantes().remove(participante);
		participante.setTemporada(null);

		return participante;
	}


	//bi-directional many-to-one association to LigaHistorico
	@ManyToOne
	@JoinColumn(name="liga_datos_id")
	public LigaHistorico getLigaHistorico() {
		return this.ligaHistorico;
	}

	public void setLigaHistorico(LigaHistorico ligaHistorico) {
		this.ligaHistorico = ligaHistorico;
	}

}