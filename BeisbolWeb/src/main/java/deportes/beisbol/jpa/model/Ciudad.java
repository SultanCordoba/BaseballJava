package deportes.beisbol.jpa.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Set;


/**
 * The persistent class for the ciudad database table.
 * 
 */
@Entity
// @NamedQuery(name="Ciudad.findAll", query="SELECT c FROM Ciudad c")
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private Timestamp fechaActualizacion;
	private String nombre;
	private int version;
	private Estado estado;
	private Set<Parque> parques;

	public Ciudad() {
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


	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}


	//bi-directional many-to-one association to Estado
	@ManyToOne()
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	//bi-directional many-to-one association to Parque
	@OneToMany(mappedBy="ciudad", fetch=FetchType.LAZY)
	public Set<Parque> getParques() {
		return this.parques;
	}

	public void setParques(Set<Parque> parques) {
		this.parques = parques;
	}

	public Parque addParque(Parque parque) {
		getParques().add(parque);
		parque.setCiudad(this);

		return parque;
	}

	public Parque removeParque(Parque parque) {
		getParques().remove(parque);
		parque.setCiudad(null);

		return parque;
	}

	public String nombreCiudad() {
		Joiner joiner = Joiner.on(", ").skipNulls();
		ArrayList<String> ciudadArreglo = new ArrayList<>();
		
		try {
			ciudadArreglo.add(Strings.emptyToNull(this.getNombre().replace("Indefinido", "")));
			ciudadArreglo.add(Strings.emptyToNull(this.getEstado().getNombreEs().replace("Indefinido", "")));
			ciudadArreglo.add(Strings.emptyToNull(this.getEstado().getPai().getAbreviaturaEs().replace("Indefinido", "")));
		} catch (NullPointerException npe) {
			ciudadArreglo.add(Strings.emptyToNull(""));
		}
		
		return Strings.nullToEmpty(joiner.join(ciudadArreglo));
	}
}