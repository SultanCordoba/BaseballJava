package deportes.beisbol.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Joiner;

import deportes.core.interfaces.ContrincanteInterfaz;
import deportes.core.interfaces.PartidoInterfaz;
import deportes.core.util.JsonDateSerializer;
import deportes.core.util.JsonDateDeserializer;

@JsonTypeName("partidoBeisbol")
public class PartidoBeisbol implements PartidoInterfaz {

	@JsonSerialize(using = JsonDateSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)	
	private LocalDate fechaRealizacion;
	
	private String comentario;
	private ContrincanteBeisbol equipoLocal;
	private ContrincanteBeisbol equipoVisita;
	private int entradas;
	private String claveMilb;
	private ParqueBeisbol parque;
	
	private String fechaString;
	private String partidoString;
	private Short partidoId;
	
	public Short getPartidoId() {
		return partidoId;
	}

	public void setPartidoId(Short partidoId) {
		this.partidoId = partidoId;
	}

	public ParqueBeisbol getParque() {
		return parque;
	}

	public void setParque(ParqueBeisbol parque) {
		this.parque = parque;
	}

	public ContrincanteBeisbol getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(ContrincanteBeisbol equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public ContrincanteBeisbol getEquipoVisita() {
		return equipoVisita;
	}

	public void setEquipoVisita(ContrincanteBeisbol equipoVisita) {
		this.equipoVisita = equipoVisita;
	}

	public int getEntradas() {
		return entradas;
	}

	public void setEntradas(int entradas) {
		this.entradas = entradas;
	}

	public String getClaveMilb() {
		return claveMilb;
	}

	public void setClaveMilb(String claveMilb) {
		this.claveMilb = claveMilb;
	}

	public int getNumJuego() {
		return numJuego;
	}

	public void setNumJuego(int numJuego) {
		this.numJuego = numJuego;
	}

	private int numJuego;
	
	public PartidoBeisbol() {
		this.entradas = 9;
		this.numJuego = 1;
	}
	
	@Override
	public LocalDate getFechaRealizacion() {
		return fechaRealizacion;
	}
	
	@Override
	public void setFechaRealizacion(LocalDate fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}
	
	@Override
	public String getComentario() {
		return comentario;
	}
	
	@Override
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	@Override
	public ContrincanteBeisbol getLocal() {
		return equipoLocal;
	}
	
	@Override
	public void setLocal(ContrincanteInterfaz equipoLocal) {
		this.equipoLocal = (ContrincanteBeisbol) equipoLocal;
	}
	
	@Override
	public ContrincanteBeisbol getVisita() {
		return equipoVisita;
	}
	
	@Override
	public void setVisita(ContrincanteInterfaz equipoVisita) {
		this.equipoVisita = (ContrincanteBeisbol) equipoVisita;
	}
	
	
	
	public String generaPartidoAbreviado() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		ArrayList<String> datosJuego = new ArrayList<>();
		ArrayList<String> extraDatosJuego = new ArrayList<>();
		String formattedDate = this.fechaRealizacion.format(formatter);
		
		if (this.numJuego > 1) {
			extraDatosJuego.add(String.valueOf(this.entradas));
			extraDatosJuego.add(String.valueOf(this.numJuego));
			formattedDate += "(" + Joiner.on("-").join(extraDatosJuego) + ")";
		}
		else {
			if (this.entradas != 9) {
				formattedDate += "(" + String.valueOf(this.entradas) + ")";
			}
		}
		
		datosJuego.add(formattedDate);
		datosJuego.add(this.equipoVisita.getEquipo().getSiglas());
		datosJuego.add(String.valueOf(this.equipoVisita.getScore()));
		datosJuego.add(String.valueOf(this.equipoLocal.getScore()));
		datosJuego.add(this.equipoLocal.getEquipo().getSiglas());
		
		return Joiner.on(",").skipNulls().join(datosJuego);
	}
	
	public String generaPartidoResumen() {
		
		ArrayList<String> datosJuego = new ArrayList<>();
		// ArrayList<String> extraDatosJuego = new ArrayList<>();

		datosJuego.add(this.equipoVisita.getEquipo().getNombreTabla());
		datosJuego.add(String.valueOf(this.equipoVisita.getScore()));
		datosJuego.add("-");
		datosJuego.add(String.valueOf(this.equipoLocal.getScore()));
		datosJuego.add(this.equipoLocal.getEquipo().getNombreTabla());
		
		return Joiner.on(" ").skipNulls().join(datosJuego);
	}
	
	public void asignaPartidoString() {
		this.partidoString = generaPartidoResumen();
	}
	
	public String getFechaString() {
		return fechaString;
	}

	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}

	public String getPartidoString() {
		return partidoString;
	}

	public void setPartidoString(String partidoString) {
		this.partidoString = partidoString;
	}

	public void generaFechaString(String idioma) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		this.fechaString = this.fechaRealizacion.format(dtf);
		
	}
	
	public void generaFechaString() {
		generaFechaString("ES");
	}

}
