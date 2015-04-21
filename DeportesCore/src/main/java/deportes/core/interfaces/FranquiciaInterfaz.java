package deportes.core.interfaces;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: L00596254
 * Date: 10/15/13
 * Time: 11:25 AM
 * To change this template use File | Settings | File Templates.
 */
public interface FranquiciaInterfaz extends DeporteBasicoInterfaz {
    public Collection<? extends DeporteRangoFechaInterfaz> getNombres();

    public void setNombres(Collection<? extends DeporteRangoFechaInterfaz> nombres);

    public Collection<? extends DeporteRangoFechaInterfaz> getEscudos();

    public void setEscudos(Collection<? extends DeporteRangoFechaInterfaz> escudos);

    public Collection<? extends DeporteRangoFechaInterfaz> getParques();

    public void setParques(Collection<? extends DeporteRangoFechaInterfaz> parques);
}
