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
@Table(name="record_int")
public class RecordInt implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private short id;
	private String nombreGrupo;
	private Timestamp fechaActualizacion;	
	private Record record;
	private Idioma idioma;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public short getId() {
		return id;
	}
	
	public void setId(short id) {
		this.id = id;
	}
	
	
	public String getNombreGrupo() {
		return nombreGrupo;
	}
	
	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}
	
	@Column(name="fecha_actualizacion")
	public Timestamp getFechaActualizacion() {
		return fechaActualizacion;
	}
	
	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
	//bi-directional many-to-one association to Record
	@ManyToOne
	public Record getRecord() {
		return record;
	}
	
	public void setRecord(Record record) {
		this.record = record;
	}
	
	@ManyToOne
	public Idioma getIdioma() {
		return idioma;
	}
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
	
}
