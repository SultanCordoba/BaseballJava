package deportes.beisbol.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="franquicia_vista")
public class FranquiciaVista implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private short id;
	private String nombreTablasEs;
	private String nombreCompletoEs;
	private String nombreCompletoEn;
	private String nombrePais;
	
	private Liga liga;
	private Franquicia franquicia;
	
	public FranquiciaVista() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public short getId() {
		return id;
	}
	
	public void setId(short id) {
		this.id = id;
	}
	
	@Column(name="nombre_tablas_es")
	public String getNombreTablasEs() {
		return nombreTablasEs;
	}
	
	public void setNombreTablasEs(String nombreTablasEs) {
		this.nombreTablasEs = nombreTablasEs;
	}
	
	@Column(name="nombre_completo_es")
	public String getNombreCompletoEs() {
		return nombreCompletoEs;
	}
	
	public void setNombreCompletoEs(String nombreCompletoEs) {
		this.nombreCompletoEs = nombreCompletoEs;
	}
	
	@Column(name="nombre_completo_en")
	public String getNombreCompletoEn() {
		return nombreCompletoEn;
	}
	
	public void setNombreCompletoEn(String nombreCompletoEn) {
		this.nombreCompletoEn = nombreCompletoEn;
	}
	
	@Column(name="nombre_pais")
	public String getNombrePais() {
		return nombrePais;
	}
	
	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}
	
	@ManyToOne
	public Liga getLiga() {
		return liga;
	}
	
	public void setLiga(Liga liga) {
		this.liga = liga;
	}
	
	@ManyToOne
	public Franquicia getFranquicia() {
		return franquicia;
	}
	
	public void setFranquicia(Franquicia franquicia) {
		this.franquicia = franquicia;
	}
	
}
