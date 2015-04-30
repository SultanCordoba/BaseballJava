package deportes.beisbol;

import java.io.IOException;

import deportes.beisbol.lectores.LectorPartidosArchivo;

public class AppArchivo {
	public static void main(String[] args) {
		
		LectorPartidosArchivo lectorArchivo;
		
		try {
			lectorArchivo = new LectorPartidosArchivo();
			lectorArchivo.obtieneJuegos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
