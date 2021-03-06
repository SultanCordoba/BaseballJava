/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deportes.core.interfaces;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import deportes.beisbol.model.ContrincanteBeisbol;

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
public interface ContrincanteInterfaz extends DeporteBasicoInterfaz {

    static final String VISITA = "V";
    static final String LOCAL = "L";

    public short getScore();

    public void setScore(short score);

    public EquipoInterfaz getEquipo();

    public void setEquipo(EquipoInterfaz equipo);
}
