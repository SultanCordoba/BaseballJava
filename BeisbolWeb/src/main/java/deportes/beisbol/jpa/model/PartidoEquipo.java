package deportes.beisbol.jpa.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the partido_equipo database table.
 * 
 */
@Entity
@Table(name="partido_equipo")
@NamedQuery(name="PartidoEquipo.findAll", query="SELECT p FROM PartidoEquipo p")
public class PartidoEquipo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private byte carreras;
	private byte errores;
	private Date fechaActualizacion;
	private byte gano;
	private byte hits;
	private String localia;
	private byte perdio;
	
	public Partido partido;
	public Equipo equipo;

	public PartidoEquipo() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public byte getCarreras() {
		return this.carreras;
	}

	public void setCarreras(byte carreras) {
		this.carreras = carreras;
	}

	public byte getErrores() {
		return this.errores;
	}

	public void setErrores(byte errores) {
		this.errores = errores;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_actualizacion")
	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}


	public byte getGano() {
		return this.gano;
	}

	public void setGano(byte gano) {
		this.gano = gano;
	}


	public byte getHits() {
		return this.hits;
	}

	public void setHits(byte hits) {
		this.hits = hits;
	}


	public String getLocalia() {
		return this.localia;
	}

	public void setLocalia(String localia) {
		this.localia = localia;
	}

	public byte getPerdio() {
		return this.perdio;
	}

	public void setPerdio(byte perdio) {
		this.perdio = perdio;
	}


	//bi-directional many-to-one association to Partido
	@ManyToOne
	@JoinColumn(name="partido_id")
	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	
	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="equipo_id")
	public Equipo getEquipo() {
		return equipo;
	}


	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
}