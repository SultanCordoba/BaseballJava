package deportes.beisbol.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.google.common.base.Joiner;

public class TemporadaEquipo {
	private String temporadaNombre;
	private String etapaNombre;
	private int ganados;
	private int perdidos;
	private boolean campeon;
	private short participanteId;

	public boolean isCampeon() {
		return campeon;
	}

	public void setCampeon(boolean campeon) {
		this.campeon = campeon;
	}

	public String getTemporadaNombre() {
		return temporadaNombre;
	}
	
	public void setTemporadaNombre(String temporadaNombre) {
		this.temporadaNombre = temporadaNombre;
	}
	
	public String getEtapaNombre() {
		return etapaNombre;
	}
	
	public void setEtapaNombre(String etapaNombre) {
		this.etapaNombre = etapaNombre;
	}
	
	public int getGanados() {
		return ganados;
	}
	
	public void setGanados(int ganados) {
		this.ganados = ganados;
	}
	
	public int getPerdidos() {
		return perdidos;
	}
	
	public void setPerdidos(int perdidos) {
		this.perdidos = perdidos;
	}	
	
	public String getRecordTotal() {
		return Integer.toString(ganados) + " - " + Integer.toString(perdidos);
	}
	
	public String getPctjeString() {
		double porcentaje = 0;
		
		if (perdidos > 0) {
			porcentaje = (ganados / ((double) ganados + perdidos));
		}
		
		NumberFormat formatter = new DecimalFormat("0.000");
		return formatter.format(porcentaje);
	}

	public short getParticipanteId() {
		return participanteId;
	}

	public void setParticipanteId(short participanteId) {
		this.participanteId = participanteId;
	}
}
