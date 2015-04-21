package deportes.beisbol.jpa.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;
import java.sql.Timestamp;


/**
 * The persistent class for the equipo database table.
 * 
 */
@Entity
@NamedQueries({
	/* @NamedQuery(name="Equipo.findAll", query="SELECT e FROM Equipo e"), */
	@NamedQuery(name="Equipo.findCampeon", query="SELECT e FROM Equipo e " + 
	   "WHERE e.participante.temporada.id = :id AND e.campeon = 1")
})
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private String abreviatura;
	private String archivoEscudo;
	private byte campeon;
	private Timestamp fechaActualizacion;
	private Date fechaFin;
	private Date fechaInicio;
	private String nombreCompletoEs;
	private String nombreTablasEs;
	private FranquiciaHistorico franquiciaHistorico;
	private Participante participante;
	private Parque parque;
	private Set<PartidoEquipo> partidoEquipos;

	public Equipo() {

	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}


	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}


	@Column(name="archivo_escudo")
	public String getArchivoEscudo() {
		return this.archivoEscudo;
	}

	public void setArchivoEscudo(String archivoEscudo) {
		this.archivoEscudo = archivoEscudo;
	}


	public byte getCampeon() {
		return this.campeon;
	}

	public void setCampeon(byte campeon) {
		this.campeon = campeon;
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

	@Column(name="nombre_completo_es")
	public String getNombreCompletoEs() {
		return this.nombreCompletoEs;
	}

	public void setNombreCompletoEs(String nombreCompletoEs) {
		this.nombreCompletoEs = nombreCompletoEs;
	}


	@Column(name="nombre_tablas_es")
	public String getNombreTablasEs() {
		return this.nombreTablasEs;
	}

	public void setNombreTablasEs(String nombreTablasEs) {
		this.nombreTablasEs = nombreTablasEs;
	}

	//bi-directional many-to-one association to FranquiciaHistorico
	@ManyToOne
	@JoinColumn(name="parque_id")
	public Parque getParque() {
		return this.parque;
	}
	
	public void setParque(Parque parque) {
		this.parque = parque;
	}
	
	//bi-directional many-to-one association to FranquiciaHistorico
	@ManyToOne
	@JoinColumn(name="franquicia_historico_id")
	public FranquiciaHistorico getFranquiciaHistorico() {
		return this.franquiciaHistorico;
	}

	public void setFranquiciaHistorico(FranquiciaHistorico franquiciaHistorico) {
		this.franquiciaHistorico = franquiciaHistorico;
	}


	//bi-directional many-to-one association to Participante
	@ManyToOne
	public Participante getParticipante() {
		return this.participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	
	@OneToMany(mappedBy="partido")
	public Set<PartidoEquipo> getPartidoEquipos() {
		return partidoEquipos;
	}

	public void setPartidoEquipos(Set<PartidoEquipo> partidoEquipos) {
		this.partidoEquipos = partidoEquipos;
	}
	
	public PartidoEquipo addPartidoEquipo(PartidoEquipo partidoEquipo) {
		getPartidoEquipos().add(partidoEquipo);
		partidoEquipo.setEquipo(this);

		return partidoEquipo;
	}
}