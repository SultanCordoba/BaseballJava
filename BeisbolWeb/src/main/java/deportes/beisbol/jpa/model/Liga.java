package deportes.beisbol.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the liga database table.
 * 
 */
@Entity
@NamedQuery(name="Liga.findAll", query="SELECT l FROM Liga l")
public class Liga implements Serializable {
	private static final long serialVersionUID = 1L;
	private byte id;
	private byte activa;
	private String descripcionBreveEn;
	private String descripcionBreveEs;
	private byte disponible;
	private Timestamp fechaActualizacion;
	private String nombreEn;
	private String nombreEs;
	private String siglasEn;
	private String siglasEs;
	private int version;
	private Set<Franquicia> franquicias;
	private Set<LigaHistorico> ligaHistoricos;

	public Liga() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
	}


	public byte getActiva() {
		return this.activa;
	}

	public void setActiva(byte activa) {
		this.activa = activa;
	}


	@Column(name="descripcion_breve_en")
	public String getDescripcionBreveEn() {
		return this.descripcionBreveEn;
	}

	public void setDescripcionBreveEn(String descripcionBreveEn) {
		this.descripcionBreveEn = descripcionBreveEn;
	}


	@Column(name="descripcion_breve_es")
	public String getDescripcionBreveEs() {
		return this.descripcionBreveEs;
	}

	public void setDescripcionBreveEs(String descripcionBreveEs) {
		this.descripcionBreveEs = descripcionBreveEs;
	}


	public byte getDisponible() {
		return this.disponible;
	}

	public void setDisponible(byte disponible) {
		this.disponible = disponible;
	}


	@Column(name="fecha_actualizacion")
	public Timestamp getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}


	@Column(name="nombre_en")
	public String getNombreEn() {
		return this.nombreEn;
	}

	public void setNombreEn(String nombreEn) {
		this.nombreEn = nombreEn;
	}


	@Column(name="nombre_es")
	public String getNombreEs() {
		return this.nombreEs;
	}

	public void setNombreEs(String nombreEs) {
		this.nombreEs = nombreEs;
	}


	@Column(name="siglas_en")
	public String getSiglasEn() {
		return this.siglasEn;
	}

	public void setSiglasEn(String siglasEn) {
		this.siglasEn = siglasEn;
	}


	@Column(name="siglas_es")
	public String getSiglasEs() {
		return this.siglasEs;
	}

	public void setSiglasEs(String siglasEs) {
		this.siglasEs = siglasEs;
	}

    @Column(name="version")
	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}


	//bi-directional many-to-one association to Franquicia
	@OneToMany(mappedBy="liga")
	public Set<Franquicia> getFranquicias() {
		return this.franquicias;
	}

	public void setFranquicias(Set<Franquicia> franquicias) {
		this.franquicias = franquicias;
	}

	public Franquicia addFranquicia(Franquicia franquicia) {
		getFranquicias().add(franquicia);
		franquicia.setLiga(this);

		return franquicia;
	}

	public Franquicia removeFranquicia(Franquicia franquicia) {
		getFranquicias().remove(franquicia);
		franquicia.setLiga(null);

		return franquicia;
	}

	
	//bi-directional many-to-one association to LigaHistorico
	@OneToMany(mappedBy="liga")
	public Set<LigaHistorico> getLigaHistoricos() {
		return this.ligaHistoricos;
	}

	public void setLigaHistoricos(Set<LigaHistorico> ligaHistoricos) {
		this.ligaHistoricos = ligaHistoricos;
	}

	public LigaHistorico addLigaHistorico(LigaHistorico ligaHistorico) {
		getLigaHistoricos().add(ligaHistorico);
		ligaHistorico.setLiga(this);

		return ligaHistorico;
	}

	public LigaHistorico removeLigaHistorico(LigaHistorico ligaHistorico) {
		getLigaHistoricos().remove(ligaHistorico);
		ligaHistorico.setLiga(null);

		return ligaHistorico;
	}
}