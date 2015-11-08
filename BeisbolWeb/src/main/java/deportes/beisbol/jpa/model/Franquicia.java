package deportes.beisbol.jpa.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the franquicia database table.
 * 
 */
@Entity
// @NamedQuery(name="Franquicia.findAll", query="SELECT f FROM Franquicia f")
public class Franquicia implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private String abreviatura;
	private String archivoEscudo;
	private Timestamp fechaActualizacion;
	private String nombreCompletoEs;
	private String nombreTablasEs;
	private Parque parque;
	private byte versionFranquicia;
	private Club club;
	private Liga liga;
	private Set<FranquiciaHistorico> franquiciaHistoricos;
	private Set<FranquiciaVista> franquiciaVistas;

	public Franquicia() {
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


	//bi-directional many-to-one association to Club
	@ManyToOne
	public Club getClub() {
		return this.club;
	}

	public void setClub(Club club) {
		this.club = club;
	}


	//bi-directional many-to-one association to Liga
	@ManyToOne
	public Liga getLiga() {
		return this.liga;
	}

	public void setLiga(Liga liga) {
		this.liga = liga;
	}


	//bi-directional many-to-one association to FranquiciaHistorico
	@OneToMany(mappedBy="franquicia", fetch=FetchType.EAGER)
	@OrderBy("fechaInicio")
	public Set<FranquiciaHistorico> getFranquiciaHistoricos() {
		return this.franquiciaHistoricos;
	}

	public void setFranquiciaHistoricos(Set<FranquiciaHistorico> franquiciaHistoricos) {
		this.franquiciaHistoricos = franquiciaHistoricos;
	}

	public FranquiciaHistorico addFranquiciaHistorico(FranquiciaHistorico franquiciaHistorico) {
		getFranquiciaHistoricos().add(franquiciaHistorico);
		franquiciaHistorico.setFranquicia(this);

		return franquiciaHistorico;
	}

	public FranquiciaHistorico removeFranquiciaHistorico(FranquiciaHistorico franquiciaHistorico) {
		getFranquiciaHistoricos().remove(franquiciaHistorico);
		franquiciaHistorico.setFranquicia(null);

		return franquiciaHistorico;
	}

	
	//bi-directional many-to-one association to LigaHistorico
	@OneToMany(mappedBy="liga")
	public Set<FranquiciaVista> getFranquiciaVistas() {
		return this.franquiciaVistas;
	}

	public void setFranquiciaVistas(Set<FranquiciaVista> franquiciaVistas) {
		this.franquiciaVistas = franquiciaVistas;
	}	
}