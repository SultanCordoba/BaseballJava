/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deportes.core.util;

import deportes.core.interfaces.DeporteBasicoInterfaz;
import deportes.core.interfaces.DeporteRangoFechaInterfaz;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author L00596254
 */
/**
 * Contiene un nombre y el rango de fechas en que ese nombre es usado.
 */
public class NombreTiempo implements DeporteRangoFechaInterfaz, DeporteBasicoInterfaz {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String nombre;
    private String siglas;
    private DateTimeFormatter formateadorFecha;

    /**
     * Constructor. Inicializa la fecha de inicio y fecha de final del rango con
     * las constantes definidas en la interfaz <b>RangoFecha</b>
     *
     * @throws ParseException
     */
    public NombreTiempo() throws DeportesException {
        super();
        formateadorFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        this.fechaInicio = LocalDate.parse(FECHA_INICIO_DEFAULT, formateadorFecha);
        this.fechaFin = LocalDate.parse(FECHA_FINAL_DEFAULT, formateadorFecha);
    }

    /**
     * @return the fechaInicio
     */
    @Override
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio - Fecha a asignar como inicio del rango, como Date
     */
    @Override
    public void setFechaInicio(LocalDate fechaInicio) throws DeportesException {
        if (fechaInicio.isAfter(this.fechaFin)) {
            throw new DeportesException("La fecha de inicio del rango no puede ser mayor a la final.");
        }
        this.fechaInicio = fechaInicio;
    }

    /**
     * @param fechaInicio - Fecha a asignar como inicio del rango, como String
     */
    public void setFechaInicio(String fechaInicio) throws DeportesException {
        this.setFechaInicio(LocalDate.parse(fechaInicio,formateadorFecha));
    }

    /**
     * @return the fechaFin
     */
    @Override
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin - Fecha a asignar como fin del rango, como Date
     */
    @Override
    public void setFechaFin(LocalDate fechaFin) throws DeportesException {
        if (fechaFin.isBefore(this.fechaInicio)) {
            throw new DeportesException("La fecha final del rango no puede ser menor al inicio.");
        }
        this.fechaFin = fechaFin;
    }

    /**
     * @param fechaFin - Fecha a asignar como fin del rango, como String
     */
    public void setFechaFin(String fechaFin) throws DeportesException {
        /*try {
            this.setFechaFin((Date) formateador.parse(fechaFin));
        } catch (ParseException ex) {
            throw new DeportesException("Error en el formato de la fecha.");
        }*/
        this.setFechaFin(LocalDate.parse(fechaFin,formateadorFecha));
    }

    /**
     *
     * @param fecha
     * @return un booleano indicado si la fecha está en el rango definido en el
     * objeto.
     */
    @Override
    public boolean fechaEnRango(LocalDate fecha) {
        return fecha.isAfter(this.fechaInicio) && fecha.isBefore(this.fechaFin);

    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the siglas
     */
    public String getSiglas() {
        return siglas;
    }

    /**
     * @param siglas the siglas to set
     */
    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }
}
