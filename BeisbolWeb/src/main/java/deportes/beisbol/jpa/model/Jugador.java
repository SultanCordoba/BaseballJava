package deportes.beisbol.jpa.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the jugador database table.
 * 
 */
@Entity
public class Jugador implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private String apellidoMaterno;
	private String apellidoPaterno;
	private String apodo;
	private String batea;
	private Ciudad ciudad;
	private Timestamp fechaActualizacion;
	private Date fechaNacimiento;
	private String nombres;
	private String posicion;
	private String tira;
	private String anyoNacimiento;
	private String claveSABR;
	private String bbRef;
	private String nombreBusqueda;
	private Set<Roster> rosters;

	public Jugador() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}


	@Column(name="apellido_materno")
	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}


	@Column(name="apellido_paterno")
	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}


	public String getApodo() {
		return this.apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}


	public String getBatea() {
		return this.batea;
	}

	public void setBatea(String batea) {
		this.batea = batea;
	}


	@ManyToOne
	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}


	@Column(name="fecha_actualizacion")
	public Timestamp getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getPosicion() {
		return this.posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}


	public String getTira() {
		return this.tira;
	}

	public void setTira(String tira) {
		this.tira = tira;
	}

	@Column(name="anio_nacimiento")
	public String getAnyoNacimiento() {
		return anyoNacimiento;
	}

	public void setAnyoNacimiento(String anyoNacimiento) {
		this.anyoNacimiento = anyoNacimiento;
	}

	@Column(name="clave_SABR")
	public String getClaveSABR() {
		return claveSABR;
	}

	public void setClaveSABR(String claveSABR) {
		this.claveSABR = claveSABR;
	}

	@Column(name="BB_Ref")
	public String getBbRef() {
		return bbRef;
	}

	public void setBbRef(String bbRef) {
		this.bbRef = bbRef;
	}


	public String getNombreBusqueda() {
		return nombreBusqueda;
	}


	public void setNombreBusqueda(String nombreBusqueda) {
		this.nombreBusqueda = nombreBusqueda;
	}
	
	//bi-directional many-to-one association to FranquiciaHistorico
	@OneToMany(mappedBy="jugador")
	public Set<Roster> getRosters() {
		return rosters;
	}

	public void setRosters(Set<Roster> rosters) {
		this.rosters = rosters;
	}
	
	public Roster addRoster(Roster roster) {
		getRosters().add(roster);
		roster.setJugador(this);
		
		return roster;
	}
}