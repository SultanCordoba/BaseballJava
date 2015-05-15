/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deportes.core.interfaces;

/**
 *
 * @author L00596254
 *
 * Extiende la interfaz de nombre, asumiendo que el objeto puede manejar siglas
 * o abreviaturas.
 */
public interface NombreInterfaz extends DeporteBasicoInterfaz {

    public String getSiglas();

    public void setSiglas(String siglas);
}
