package deportes.beisbol.model;

import deportes.core.interfaces.DeporteRecordInterfaz;

public class RecordBeisbol implements DeporteRecordInterfaz {

	private String nombre;
	private short ganados;
	private short perdidos;
	private double porcentaje;
	private String nombreGrupo;
	private String nombreVuelta;
	private int idVuelta;
	private String pctjeString;
	private String juegosDetras;
	private String nombreAbrev;
	private short participanteId;
	
	@Override
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public short getGanados() {
		return ganados;
	}
	
	@Override
	public void setGanados(short ganados) {
		this.ganados = ganados;
	}
	
	@Override
	public short getPerdidos() {
		return perdidos;
	}
	
	@Override
	public void setPerdidos(short perdidos) {
		this.perdidos = perdidos;
	}
	
	@Override
	public double getPorcentaje() {
		return porcentaje;
	}
	
	@Override
	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getNombreGrupo() {
		return nombreGrupo;
	}

	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	public String getNombreVuelta() {
		return nombreVuelta;
	}

	public void setNombreVuelta(String nombreVuelta) {
		this.nombreVuelta = nombreVuelta;
	}

	public int getIdVuelta() {
		return idVuelta;
	}

	public void setIdVuelta(int idVuelta) {
		this.idVuelta = idVuelta;
	}

	public String getPctjeString() {
		return pctjeString;
	}

	public void setPctjeString(String pctjeString) {
		this.pctjeString = pctjeString;
	}

	public String getJuegosDetras() {
		return juegosDetras;
	}

	public void setJuegosDetras(String juegosDetras) {
		this.juegosDetras = juegosDetras;
	}

	public String getNombreAbrev() {
		return nombreAbrev;
	}

	public void setNombreAbrev(String nombreAbrev) {
		this.nombreAbrev = nombreAbrev;
	}

	public short getParticipanteId() {
		return participanteId;
	}

	public void setParticipanteId(short participanteId) {
		this.participanteId = participanteId;
	}
}
