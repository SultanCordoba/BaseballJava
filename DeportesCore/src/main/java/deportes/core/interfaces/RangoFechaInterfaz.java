/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deportes.core.interfaces;

import java.time.LocalDate;

import deportes.core.util.DeportesException;

/**
 *
 * @author L00596254
 *
 * Hay atributos deportivos que tienen una determinada vigencia.
 */
public interface RangoFechaInterfaz {

    static final String FECHA_INICIO_DEFAULT = "1900-01-01";
    static final String FECHA_FINAL_DEFAULT = "2100-12-31";

    LocalDate getFechaInicio();

    void setFechaInicio(LocalDate fechaInicio) throws DeportesException;

    LocalDate getFechaFin();

    void setFechaFin(LocalDate fechaFin) throws DeportesException;

    boolean fechaEnRango(LocalDate fecha);
}
