/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deportes.core.util;

/**
 *
 * @author L00596254
 */
public class DeportesException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String mensaje;

    public DeportesException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    public DeportesException() {
        super();
        this.mensaje = "Desconocido";
    }

    public String getError() {
        return mensaje;
    }
}
