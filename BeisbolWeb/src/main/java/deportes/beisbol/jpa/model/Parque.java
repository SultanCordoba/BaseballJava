package deportes.beisbol.jpa.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the parque database table.
 * 
 */
@Entity
@NamedQuery(name="Parque.findAll", query="SELECT p FROM Parque p")
public class Parque implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private Timestamp fechaActualizacion;
	private String nombre;
	private byte versionParque;
	private Ciudad ciudad;
	private Set<Partido> partidos;
	private Set<Franquicia> franquicias;
	private Set<FranquiciaHistorico> franquiciaHistoricos;
	private Set<Equipo> equipos;

	public Parque() {
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


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Column(name="version_parque")
	public byte getVersionParque() {
		return this.versionParque;
	}

	public void setVersionParque(byte versionParque) {
		this.versionParque = versionParque;
	}


	//bi-directional many-to-one association to Ciudad
	@ManyToOne(fetch = FetchType.LAZY)
	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}


	//bi-directional many-to-one association to Partido
	@OneToMany(mappedBy="parque")
	public Set<Partido> getPartidos() {
		return this.partidos;
	}

	public void setPartidos(Set<Partido> partidos) {
		this.partidos = partidos;
	}

	public Partido addPartido(Partido partido) {
		getPartidos().add(partido);
		partido.setParque(this);

		return partido;
	}

	public Partido removePartido(Partido partido) {
		getPartidos().remove(partido);
		partido.setParque(null);

		return partido;
	}
	
	//bi-directional many-to-one association to Partido
	//@OneToMany(mappedBy="parque", fetch=FetchType.EAGER)
	@OneToMany(mappedBy="parque")
	public Set<Franquicia> getFranquicias() {
		return this.franquicias;
	}

	public void setFranquicias(Set<Franquicia> franquicias) {
		this.franquicias = franquicias;
	}

	public Franquicia addFranquicia(Franquicia franquicia) {
		getFranquicias().add(franquicia);
		franquicia.setParque(this);

		return franquicia;
	}

	public Franquicia removeFranquicia(Franquicia franquicia) {
		getFranquicias().remove(franquicia);
		franquicia.setParque(null);

		return franquicia;
	}

	
	//bi-directional many-to-one association to Partido
	@OneToMany(mappedBy="parque")
	public Set<FranquiciaHistorico> getFranquiciaHistoricos() {
		return this.franquiciaHistoricos;
	}

	public void setFranquiciaHistoricos(Set<FranquiciaHistorico> franquiciaHistoricos) {
		this.franquiciaHistoricos = franquiciaHistoricos;
	}

	public FranquiciaHistorico addFranquiciaHistorico(FranquiciaHistorico franquiciaHistorico) {
		getFranquiciaHistoricos().add(franquiciaHistorico);
		franquiciaHistorico.setParque(this);

		return franquiciaHistorico;
	}

	public FranquiciaHistorico removeFranquiciaHistorico(FranquiciaHistorico franquiciaHistorico) {
		getFranquiciaHistoricos().remove(franquiciaHistorico);
		franquiciaHistorico.setParque(null);

		return franquiciaHistorico;
	}	
	
	//bi-directional many-to-one association to Equipo
	@OneToMany(mappedBy="parque")
	public Set<Equipo> getEquipos() {
		return this.equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

	public Equipo addEquipo(Equipo equipo) {
		getEquipos().add(equipo);
		equipo.setParque(this);

		return equipo;
	}

	public Equipo removeEquipo(Equipo equipo) {
		getEquipos().remove(equipo);
		equipo.setParque(null);

		return equipo;
	}
}