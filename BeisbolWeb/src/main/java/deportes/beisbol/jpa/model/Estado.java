package deportes.beisbol.jpa.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the estado database table.
 * 
 */
@Entity
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private String abreviaturaEs;
	private Timestamp fechaActualizacion;
	private String nombreEs;
	private Set<Ciudad> ciudads;
	private Pais pai;

	public Estado() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}


	@Column(name="abreviatura_es")
	public String getAbreviaturaEs() {
		return this.abreviaturaEs;
	}

	public void setAbreviaturaEs(String abreviaturaEs) {
		this.abreviaturaEs = abreviaturaEs;
	}


	@Column(name="fecha_actualizacion")
	public Timestamp getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}


	@Column(name="nombre_es")
	public String getNombreEs() {
		return this.nombreEs;
	}

	public void setNombreEs(String nombreEs) {
		this.nombreEs = nombreEs;
	}


	//bi-directional many-to-one association to Ciudad
	@OneToMany(mappedBy="estado", fetch=FetchType.LAZY)
	public Set<Ciudad> getCiudads() {
		return this.ciudads;
	}

	public void setCiudads(Set<Ciudad> ciudads) {
		this.ciudads = ciudads;
	}

	public Ciudad addCiudad(Ciudad ciudad) {
		getCiudads().add(ciudad);
		ciudad.setEstado(this);

		return ciudad;
	}

	public Ciudad removeCiudad(Ciudad ciudad) {
		getCiudads().remove(ciudad);
		ciudad.setEstado(null);

		return ciudad;
	}


	//bi-directional many-to-one association to Pais
	@ManyToOne
	@JoinColumn(name="pais_id")
	public Pais getPai() {
		return this.pai;
	}

	public void setPai(Pais pai) {
		this.pai = pai;
	}

}