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
@Table(name="franquicia_historico_int")
public class FranquiciaHistoricoInt implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private String nombreCompleto;
	private Timestamp fechaActualizacion;
	private FranquiciaHistorico franquiciaHistorico;
	private Idioma idioma;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public short getId() {
		return id;
	}
	
	public void setId(short id) {
		this.id = id;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	@Column(name="fecha_actualizacion")
	public Timestamp getFechaActualizacion() {
		return fechaActualizacion;
	}
	
	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@ManyToOne
	public FranquiciaHistorico getFranquiciaHistorico() {
		return franquiciaHistorico;
	}

	public void setFranquiciaHistorico(FranquiciaHistorico franquiciaHistorico) {
		this.franquiciaHistorico = franquiciaHistorico;
	}

	@ManyToOne
	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
}
