/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deportes.core.util;

/**
 *
 * @author L00596254
 */
public class UbicacionGeografica {
    private String nombre;
    private String abreviatura;
    private UbicacionGeografica contenedor;
    
    public final String INDEFINIDO = "INDEFINIDO";
    
    public UbicacionGeografica() { 
        this.nombre = INDEFINIDO;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the contenedor
     */
    public UbicacionGeografica getContenedor() {
        return contenedor;
    }

    /**
     * @param contenedor the contenedor to set
     */
    public void setContenedor(UbicacionGeografica contenedor) {
        this.contenedor = contenedor;
    }

    /**
     * @return the abreviatura
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * @param abreviatura the abreviatura to set
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
    public String getFullNombre(boolean abreviado) {
        StringBuilder respuesta = new StringBuilder("");
        
        if (!nombre.equals(INDEFINIDO)) {
            respuesta.append(nombre);
            if (contenedor != null) {
                respuesta.append(",").append(contenedor.getFullNombre(abreviado));
            }
        }
        
        return respuesta.toString();
    }
}
