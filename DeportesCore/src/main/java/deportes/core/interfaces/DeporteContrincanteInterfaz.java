/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deportes.core.interfaces;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import deportes.beisbol.model.ContrincanteBeisbol;
import deportes.beisbol.model.PartidoBeisbol;

/**
 *
 * @author L00596254
 *
 * Define un contrincante en un partido. Se asume que tiene un nombre dicho
 * contrincante. Maneja un campo de score que es el que determina si el
 * contrincante ha superado a su adversario en un partido.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY,property="species")
@JsonSubTypes({
        @JsonSubTypes.Type(value=ContrincanteBeisbol.class, name="contBeisbol")
})
public interface DeporteContrincanteInterfaz extends DeporteBasicoInterfaz {

    static final String VISITA = "V";
    static final String LOCAL = "L";

    public short getScore();

    public void setScore(short score);

    public DeporteEquipoInterfaz getEquipo();

    public void setEquipo(DeporteEquipoInterfaz equipo);
}
