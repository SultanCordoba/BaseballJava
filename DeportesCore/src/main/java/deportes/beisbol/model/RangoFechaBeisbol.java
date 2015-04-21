/**
 * 
 */
package deportes.beisbol.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import com.google.common.base.Joiner;

import deportes.core.interfaces.DeporteBasicoInterfaz;
import deportes.core.interfaces.DeporteRangoFechaInterfaz;

/**
 * @author juanfriverap
 *
 */
public class RangoFechaBeisbol implements DeporteRangoFechaInterfaz,
		DeporteBasicoInterfaz {
	
	private String nombre;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private String rangoString;

    @Override
	public String getNombre() {
		return nombre;
	}


    @Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


    @Override
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}


    @Override
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


    @Override
	public LocalDate getFechaFin() {
		return fechaFin;
	}


    @Override
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	/* (non-Javadoc)
	 * @see deportes.core.interfaces.DeporteRangoFechaInterfaz#fechaEnRango(java.time.LocalDate)
	 */
	@Override
	public boolean fechaEnRango(LocalDate fecha) {
		// TODO Auto-generated method stub
		return (fecha.isAfter(fechaInicio) && fecha.isBefore(fechaFin)) ||
				fecha.equals(fechaInicio) || fecha.equals(fechaFin);
	}

	public String getRangoString() {
		return rangoString;
	}

	public void setRangoString(String rangoString) {
		this.rangoString = rangoString;
	}
	
	public void creaRangoString() {
		
		Joiner joiner = Joiner.on(" - ");
		ArrayList<String> paso = new ArrayList<>();
		
		paso.add(Integer.toString(fechaInicio.getYear()));
		
		if (fechaFin.isBefore(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
			paso.add(Integer.toString(fechaFin.getYear()));
		} else {
			paso.add("");
		}
		
		rangoString = joiner.join(paso);
	}

}
