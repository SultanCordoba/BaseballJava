/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deportes.core.interfaces;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import deportes.beisbol.model.TemporadaBeisbol;

/**
 *
 * @author L00596254
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY,property="species")
@JsonSubTypes({
        @JsonSubTypes.Type(value=TemporadaBeisbol.class, name="tempBeisbol")
})
public interface TemporadaInterfaz extends DeporteBasicoInterfaz {
    
    public Collection<? extends EtapaInterfaz> getEtapas();
    
    public void setEtapas(Collection<? extends EtapaInterfaz> etapas);
}
