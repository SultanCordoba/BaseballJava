package deportes.beisbol.jpa.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="etapa_int")
public class EtapaInt implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private short id;
	private String nombre;
	private Timestamp fechaActualizacion;	
	private Etapa etapa;
	private Idioma idioma;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public short getId() {
		return id;
	}
	
	public void setId(short id) {
		this.id = id;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name="fecha_actualizacion")
	public Timestamp getFechaActualizacion() {
		return fechaActualizacion;
	}
	
	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
	//bi-directional many-to-one association to Etapa
	@ManyToOne
	public Etapa getEtapa() {
		return etapa;
	}
	
	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}
	
	@ManyToOne
	public Idioma getIdioma() {
		return idioma;
	}
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
	
}
