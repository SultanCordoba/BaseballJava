/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deportes.core.interfaces;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import deportes.beisbol.model.EquipoBeisbol;

/**
 *
 * @author L00596254
 *
 * En el caso de una franquicia, se puede agregar el nombre que el equipo usa en
 * desplegado de tablas o resumenes.
 */

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY,property="species")
@JsonSubTypes({
        @JsonSubTypes.Type(value=EquipoBeisbol.class, name="equipoBeisbol")
})
public interface EquipoInterfaz extends NombreInterfaz {

    public String getNombreTabla();

    public void setNombreTabla(String nombreTabla);
    
    public Collection<? extends JugadorInterfaz> getJugadores();
    
    public void setJugadores(Collection<? extends JugadorInterfaz> jugadores);
}
