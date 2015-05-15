/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deportes.core.interfaces;

/**
 *
 * @author L00596254
 */
public interface JugadorInterfaz {
    
    public String getNombre();
    
    public void setNombre(String nombre);
    
    public String getApellidoPaterno();
    
    public void setApellidoPaterno(String apellidoPaterno);
    
    public String getApellidoMaterno();
    
    public void setApellidoMaterno(String apellidoMaterno);
    
    public String getNombreCompleto();
    
    public String getNombreAbreviado();
}
