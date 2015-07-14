package deportes.beisbol.jpa.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.sql.Timestamp;


/**
 * The persistent class for the partido database table.
 * 
 */
@Entity
//@NamedQuery(name="Partido.findAll", query="SELECT p FROM Partido p")
public class Partido implements Serializable {
	private static final long serialVersionUID = 1L;
	private short id;
	private String comentario;
	private byte entradas;
	private Timestamp fechaActualizacion;
	private Date fechaRealizacion;
	private byte forfeit;
	private byte juegoDia;
	private Etapa etapa;
	private Parque parque;
	private Vuelta vuelta;
	private String partidoMilb;
	private byte mostrar;
	private Set<PartidoEquipo> partidoEquipos;

	public Partido() {
		partidoEquipos = new LinkedHashSet<>();
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}


	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	public byte getEntradas() {
		return this.entradas;
	}

	public void setEntradas(byte entradas) {
		this.entradas = entradas;
	}


	@Column(name="fecha_actualizacion")
	public Timestamp getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="fecha_realizacion")
	public Date getFechaRealizacion() {
		return this.fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}


	public byte getForfeit() {
		return this.forfeit;
	}

	public void setForfeit(byte forfeit) {
		this.forfeit = forfeit;
	}


	@Column(name="juego_dia")
	public byte getJuegoDia() {
		return this.juegoDia;
	}

	public void setJuegoDia(byte juegoDia) {
		this.juegoDia = juegoDia;
	}


	//bi-directional many-to-one association to Etapa
	@ManyToOne
	public Etapa getEtapa() {
		return this.etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}


	//bi-directional many-to-one association to Parque
	@ManyToOne
	public Parque getParque() {
		return this.parque;
	}

	public void setParque(Parque parque) {
		this.parque = parque;
	}


	//bi-directional many-to-one association to Vuelta
	@ManyToOne
	public Vuelta getVuelta() {
		return this.vuelta;
	}

	public void setVuelta(Vuelta vuelta) {
		this.vuelta = vuelta;
	}

	@Column(name="partido_milb")
	public String getPartidoMilb() {
		return partidoMilb;
	}


	public byte getMostrar() {
		return mostrar;
	}


	public void setMostrar(byte mostrar) {
		this.mostrar = mostrar;
	}


	public void setPartidoMilb(String partidoMilb) {
		this.partidoMilb = partidoMilb;
	}


	@OneToMany(mappedBy="partido", cascade = CascadeType.ALL)
	public Set<PartidoEquipo> getPartidoEquipos() {
		return partidoEquipos;
	}


	public void setPartidoEquipos(Set<PartidoEquipo> partidoEquipos) {
		this.partidoEquipos = partidoEquipos;
	}
	
	public PartidoEquipo addPartidoEquipo(PartidoEquipo partidoEquipo) {		
		partidoEquipo.setPartido(this);
		getPartidoEquipos().add(partidoEquipo);

		return partidoEquipo;
	}

}