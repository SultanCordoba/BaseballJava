package deportes.beisbol.jpa.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Idioma implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private String nombre;
	private String abreviatura;
	private Timestamp fechaActualizacion;
	private Set<LigaHistoricoInt> ligaHistoricoInts;
	private Set<EtapaInt> etapaInts;
	private Set<RecordInt> recordInts;
	
	public Idioma() {} 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	
	@Column(name="fecha_actualizacion")
	public Timestamp getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
	//bi-directional many-to-one association to LigaHistorico
	@OneToMany(mappedBy="idioma")
	public Set<LigaHistoricoInt> getLigaHistoricoInts() {
		return this.ligaHistoricoInts;
	}

	public void setLigaHistoricoInts(Set<LigaHistoricoInt> ligaHistoricoInts) {
		this.ligaHistoricoInts = ligaHistoricoInts;
	}

	public LigaHistoricoInt addLigaHistoricoInt(LigaHistoricoInt ligaHistoricoInt) {
		getLigaHistoricoInts().add(ligaHistoricoInt);
		ligaHistoricoInt.setIdioma(this);
		
		return ligaHistoricoInt;
	}

	public LigaHistoricoInt removeLigaHistoricoInt(LigaHistoricoInt ligaHistoricoInt) {
		getLigaHistoricoInts().remove(ligaHistoricoInt);
		ligaHistoricoInt.setIdioma(null);

		return ligaHistoricoInt;
	}
	
	//bi-directional many-to-one association to Etapa
	@OneToMany(mappedBy="idioma")
	public Set<EtapaInt> getEtapaInts() {
		return this.etapaInts;
	}

	public void setEtapaInts(Set<EtapaInt> etapaInts) {
		this.etapaInts = etapaInts;
	}

	public EtapaInt addEtapaInt(EtapaInt etapaInt) {
		getEtapaInts().add(etapaInt);
		etapaInt.setIdioma(this);
		
		return etapaInt;
	}

	public EtapaInt removeEtapaInt(EtapaInt etapaInt) {
		getEtapaInts().remove(etapaInt);
		etapaInt.setIdioma(null);

		return etapaInt;
	}

	//bi-directional many-to-one association to Etapa
	@OneToMany(mappedBy="idioma")
	public Set<RecordInt> getRecordInts() {
		return recordInts;
	}

	public void setRecordInts(Set<RecordInt> recordInts) {
		this.recordInts = recordInts;
	}
	
	public RecordInt addRecordInt(RecordInt recordInt) {
		getRecordInts().add(recordInt);
		recordInt.setIdioma(this);
		
		return recordInt;
	}

	public RecordInt removeRecordInt(RecordInt recordInt) {
		getRecordInts().remove(recordInt);
		recordInt.setIdioma(null);

		return recordInt;
	}
}
