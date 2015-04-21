/**
 * 
 */
package deportes.beisbol.jpa.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author juanfriverap
 *
 */
@Entity
@Table(name="liga_historico")
@NamedQuery(name="LigaHistorico.findAll", query="SELECT lh FROM LigaHistorico lh")
public class LigaHistorico implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private Date fechaInicio;
	private Date fechaFin;
	private Timestamp fechaActualizacion;
	private String nombre;
	private String siglas;
	private int versionLiga;
	private Set<Temporada> temporadas;
	private Set<LigaHistoricoInt> ligaHistoricoInts;
	private Liga liga;
	
	public LigaHistorico() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_fin")
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio")
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	@Column(name="fecha_actualizacion")
	public Timestamp getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Column(name="nombre")
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name="siglas")
	public String getSiglas() {
		return this.siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

    @Column(name="version_liga")
	public int getVersionLiga() {
		return this.versionLiga;
	}

	public void setVersionLiga(int versionLiga) {
		this.versionLiga = versionLiga;
	}
	
	//bi-directional many-to-one association to Temporada
	@OneToMany(mappedBy="ligaHistorico")
	public Set<Temporada> getTemporadas() {
		return this.temporadas;
	}

	public void setTemporadas(Set<Temporada> temporadas) {
		this.temporadas = temporadas;
	}

	public Temporada addTemporada(Temporada temporada) {
		getTemporadas().add(temporada);
		temporada.setLigaHistorico(this);

		return temporada;
	}

	public Temporada removeTemporada(Temporada temporada) {
		getTemporadas().remove(temporada);
		temporada.setLigaHistorico(null);

		return temporada;
	}


	//bi-directional many-to-one association to LigaHistorico
	@OneToMany(mappedBy="ligaHistorico", fetch=FetchType.EAGER)
	public Set<LigaHistoricoInt> getLigaHistoricoInts() {
		return this.ligaHistoricoInts;
	}

	public void setLigaHistoricoInts(Set<LigaHistoricoInt> ligaHistoricoInts) {
		this.ligaHistoricoInts = ligaHistoricoInts;
	}

	public LigaHistoricoInt addLigaHistoricoInt(LigaHistoricoInt ligaHistoricoInt) {
		getLigaHistoricoInts().add(ligaHistoricoInt);
		ligaHistoricoInt.setLigaHistorico(this);
		
		return ligaHistoricoInt;
	}

	public LigaHistoricoInt removeLigaHistoricoInt(LigaHistoricoInt ligaHistoricoInt) {
		getLigaHistoricoInts().remove(ligaHistoricoInt);
		ligaHistoricoInt.setLigaHistorico(null);

		return ligaHistoricoInt;
	}
	
	
	//bi-directional many-to-one association to Liga
	@ManyToOne
	public Liga getLiga() {
		return this.liga;
	}

	public void setLiga(Liga liga) {
		this.liga = liga;
	}
}
