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
public interface EtapaInterfaz extends DeporteBasicoInterfaz {
    public Collection<? extends DeportePartidoInterfaz> getPartidos();
    
    public void setPartidos(Collection<? extends DeportePartidoInterfaz> partidos);

    public Collection<? extends DeporteRecordInterfaz> getRecords();

    public void setRecords(Collection<? extends DeporteRecordInterfaz> records);
}
