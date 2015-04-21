/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deportes.core.interfaces;

import java.util.Collection;

/**
 *
 * @author L00596254
 */
public interface LigaInterfaz extends DeporteNombreInterfaz {
    public Collection<? extends TemporadaInterfaz> getTemporadas();
    
    public void setTemporadas(Collection<? extends TemporadaInterfaz> temporadas);

    public Collection<? extends FranquiciaInterfaz> getFranquicias();

    public void setFranquicias(Collection<? extends FranquiciaInterfaz> franquicias);
}
