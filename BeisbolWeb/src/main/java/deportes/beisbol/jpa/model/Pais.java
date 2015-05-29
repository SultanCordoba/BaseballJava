package deportes.beisbol.jpa.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the pais database table.
 * 
 */
@Entity
@NamedQuery(name="Pais.findAll", query="SELECT p FROM Pais p")
public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;
	private byte id;
	private String abreviaturaEs;
	private Timestamp fechaActualizacion;
	private String nombreEs;
	private Set<Club> clubs;
	private Set<Estado> estados;

	public Pais() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
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


	//bi-directional many-to-one association to Club
	@OneToMany(mappedBy="pai", fetch=FetchType.LAZY)
	public Set<Club> getClubs() {
		return this.clubs;
	}

	public void setClubs(Set<Club> clubs) {
		this.clubs = clubs;
	}

	public Club addClub(Club club) {
		getClubs().add(club);
		club.setPai(this);

		return club;
	}

	public Club removeClub(Club club) {
		getClubs().remove(club);
		club.setPai(null);

		return club;
	}


	//bi-directional many-to-one association to Estado
	@OneToMany(mappedBy="pai", fetch=FetchType.LAZY)
	public Set<Estado> getEstados() {
		return this.estados;
	}

	public void setEstados(Set<Estado> estados) {
		this.estados = estados;
	}

	public Estado addEstado(Estado estado) {
		getEstados().add(estado);
		estado.setPai(this);

		return estado;
	}

	public Estado removeEstado(Estado estado) {
		getEstados().remove(estado);
		estado.setPai(null);

		return estado;
	}

}