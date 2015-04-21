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
@Table(name="liga_historico_int")
public class LigaHistoricoInt implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private String nombre;
	private String siglas;
	private Timestamp fechaActualizacion;	
	private LigaHistorico ligaHistorico;
	private Idioma idioma;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public short getId() {
		return this.id;
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

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	
	@Column(name="fecha_actualizacion")
	public Timestamp getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}	
	
	//bi-directional many-to-one association to Liga
	@ManyToOne
	public LigaHistorico getLigaHistorico() {
		return ligaHistorico;
	}

	public void setLigaHistorico(LigaHistorico ligaHistorico) {
		this.ligaHistorico = ligaHistorico;
	}
	
	@ManyToOne
	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
}
