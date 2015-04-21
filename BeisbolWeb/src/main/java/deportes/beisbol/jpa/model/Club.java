package deportes.beisbol.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the club database table.
 * 
 */
@Entity
@NamedQuery(name="Club.findAll", query="SELECT c FROM Club c")
public class Club implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private Timestamp fechaActualizacion;
	private String nombre;
	private Pais pai;
	private Set<Franquicia> franquicias;

	public Club() {
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


	//bi-directional many-to-one association to Pais
	@ManyToOne
	@JoinColumn(name="pais_id")
	public Pais getPai() {
		return this.pai;
	}

	public void setPai(Pais pai) {
		this.pai = pai;
	}


	//bi-directional many-to-one association to Franquicia
	@OneToMany(mappedBy="club")
	public Set<Franquicia> getFranquicias() {
		return this.franquicias;
	}

	public void setFranquicias(Set<Franquicia> franquicias) {
		this.franquicias = franquicias;
	}

	public Franquicia addFranquicia(Franquicia franquicia) {
		getFranquicias().add(franquicia);
		franquicia.setClub(this);

		return franquicia;
	}

	public Franquicia removeFranquicia(Franquicia franquicia) {
		getFranquicias().remove(franquicia);
		franquicia.setClub(null);

		return franquicia;
	}

}