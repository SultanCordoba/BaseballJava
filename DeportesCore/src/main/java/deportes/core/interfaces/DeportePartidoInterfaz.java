/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deportes.core.interfaces;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import deportes.beisbol.model.PartidoBeisbol;
import deportes.beisbol.model.TemporadaBeisbol;



/**
 *
 * @author L00596254
 *
 * Define los métodos de acceso a atributos que debe tener un partido deportivo.
 * La fecha en que se realiza y los participantes en el partido. El campo
 * comentario es cuando ocurre algo extraordinario en el partido.
 */

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY,property="species")
@JsonSubTypes({
        @JsonSubTypes.Type(value=PartidoBeisbol.class, name="partidoBeisbol")
})
public interface DeportePartidoInterfaz {

    public LocalDate getFechaRealizacion();

    public void setFechaRealizacion(LocalDate fechaRealizacion);

    public DeporteContrincanteInterfaz getLocal();

    public void setLocal(DeporteContrincanteInterfaz local);

    public DeporteContrincanteInterfaz getVisita();

    public void setVisita(DeporteContrincanteInterfaz visita);

    public String getComentario();

    public void setComentario(String comentario);
}
