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
import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Strings;

import deportes.core.interfaces.DeporteBasicoInterfaz;
import deportes.core.interfaces.RangoFechaInterfaz;

/**
 * @author juanfriverap
 *
 */
public class RangoFechaBeisbol implements RangoFechaInterfaz,
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
		
		Joiner joiner = Joiner.on(" - ").skipNulls();
		ArrayList<String> paso = new ArrayList<>();
		
		paso.add(Integer.toString(fechaInicio.getYear()));
		
		String endYear = "";
		
		if (fechaFin != null) {		
			if (fechaFin.isBefore(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
				
				endYear = Integer.toString(fechaFin.getYear());
				
				if (! endYear.equals(paso.get(0))) {
					paso.add(endYear);
				}
			}
			else {
				paso.add("");
			}
		}
		else {
			paso.add("");
		}
		
		
		rangoString = joiner.join(paso);
	}
	
	@Override
	public String toString() {
		
		ToStringHelper toStringHelper = MoreObjects.toStringHelper(this);
		
		toStringHelper.add("nombre", this.nombre).add("fechaInicio", this.fechaInicio).add("fechaFin", this.fechaFin);
		
		return toStringHelper.toString();
	}

}
