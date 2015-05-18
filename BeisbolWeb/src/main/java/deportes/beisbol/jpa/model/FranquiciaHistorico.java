package deportes.beisbol.jpa.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the franquicia_historico database table.
 * 
 */
@Entity
@Table(name="franquicia_historico")
// @NamedQuery(name="FranquiciaHistorico.findAll", query="SELECT f FROM FranquiciaHistorico f")
public class FranquiciaHistorico implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private String abreviatura;
	private String archivoEscudo;
	private Timestamp fechaActualizacion;
	private Date fechaFin;
	private Date fechaInicio;
	private String nombreCompletoEs;
	private String nombreTablasEs;
	private Parque parque;
	private byte versionFranquicia;
	private Set<Equipo> equipos;
	private Franquicia franquicia;
	private Set<FranquiciaHistoricoInt> franquiciaHistoricoInts;

	public FranquiciaHistorico() {
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


	@Column(name="fecha_actualizacion")
	public Timestamp getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_fin")
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	@Temporal(TemporalType.TIMESTAMP)
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


	//bi-directional many-to-one association to Parque
	@ManyToOne
	public Parque getParque() {
		return parque;
	}


	public void setParque(Parque parque) {
		this.parque = parque;
	}


	@Column(name="version_franquicia")
	public byte getVersionFranquicia() {
		return this.versionFranquicia;
	}

	public void setVersionFranquicia(byte versionFranquicia) {
		this.versionFranquicia = versionFranquicia;
	}


	//bi-directional many-to-one association to Equipo
	@OneToMany(mappedBy="franquiciaHistorico")
	public Set<Equipo> getEquipos() {
		return this.equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

	public Equipo addEquipo(Equipo equipo) {
		getEquipos().add(equipo);
		equipo.setFranquiciaHistorico(this);

		return equipo;
	}

	public Equipo removeEquipo(Equipo equipo) {
		getEquipos().remove(equipo);
		equipo.setFranquiciaHistorico(null);

		return equipo;
	}


	//bi-directional many-to-one association to Franquicia
	@ManyToOne
	public Franquicia getFranquicia() {
		return this.franquicia;
	}

	public void setFranquicia(Franquicia franquicia) {
		this.franquicia = franquicia;
	}


	@OneToMany(mappedBy="franquiciaHistorico", fetch=FetchType.EAGER)
	public Set<FranquiciaHistoricoInt> getFranquiciaHistoricoInts() {
		return franquiciaHistoricoInts;
	}

	public void setFranquiciaHistoricoInts(
			Set<FranquiciaHistoricoInt> franquiciaHistoricoInts) {
		this.franquiciaHistoricoInts = franquiciaHistoricoInts;
	}
	
	public FranquiciaHistoricoInt addFranquiciaHistoricoInt(FranquiciaHistoricoInt franquiciaHistoricoInt) {
		getFranquiciaHistoricoInts().add(franquiciaHistoricoInt);
		franquiciaHistoricoInt.setFranquiciaHistorico(this);
		return franquiciaHistoricoInt;
	}
	
	public FranquiciaHistoricoInt removeFranquiciaHistoricoInt(FranquiciaHistoricoInt franquiciaHistoricoInt) {
		getFranquiciaHistoricoInts().remove(franquiciaHistoricoInt);
		franquiciaHistoricoInt.setFranquiciaHistorico(null);
		return franquiciaHistoricoInt;
	}

}