package deportes.core.interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: L00596254
 * Date: 9/28/13
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RecordInterfaz extends DeporteBasicoInterfaz {
    public short getGanados();

    public void setGanados(short ganados);

    public short getPerdidos();

    public void setPerdidos(short perdidos);

    public double getPorcentaje();

    public void setPorcentaje(double porcentaje);
}
