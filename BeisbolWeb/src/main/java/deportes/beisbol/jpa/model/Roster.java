package deportes.beisbol.jpa.model;

import java.io.Serializable;

import javax.persistence.*;

import com.mysema.query.annotations.QueryInit;

import java.util.Date;


/**
 * The persistent class for the roster database table.
 * 
 */
@Entity
public class Roster implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date fechaFin;
	private Date fechaInicio;
	private Date fechaActualizacion;
	private byte ordenEquipo;
	private String posicion;
	private Equipo equipo;
	private Jugador jugador;

	public Roster() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	/* @Column(name="equipo_id")
	public short getEquipoId() {
		return this.equipoId;
	}

	public void setEquipoId(short equipoId) {
		this.equipoId = equipoId;
	} */


	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin")
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	/* @Column(name="jugador_id")
	public short getJugadorId() {
		return this.jugadorId;
	}

	public void setJugadorId(short jugadorId) {
		this.jugadorId = jugadorId;
	} */


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_actualizacion")
	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Column(name="orden_equipo")
	public byte getOrdenEquipo() {
		return this.ordenEquipo;
	}

	public void setOrdenEquipo(byte ordenEquipo) {
		this.ordenEquipo = ordenEquipo;
	}


	public String getPosicion() {
		return this.posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	@ManyToOne
	@JoinColumn(name="equipo_id")
	@QueryInit("franquiciaHistorico.franquicia.liga.activa")
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@ManyToOne
	@JoinColumn(name="jugador_id")
	public Jugador getJugador() {
		return jugador;
	}


	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

}