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
    public Collection<? extends RangoFechaInterfaz> getNombres();

    public void setNombres(Collection<? extends RangoFechaInterfaz> nombres);

    public Collection<? extends RangoFechaInterfaz> getEscudos();

    public void setEscudos(Collection<? extends RangoFechaInterfaz> escudos);

    public Collection<? extends RangoFechaInterfaz> getParques();

    public void setParques(Collection<? extends RangoFechaInterfaz> parques);
}
