package deportes.beisbol;

import deportes.beisbol.archivo.LectorPartidosArchivo;

public class AppArchivo {
	public static void main(String[] args) {
		LectorPartidosArchivo lectorArchivo = new LectorPartidosArchivo();
		
		lectorArchivo.ObtenerPartidos();
	}
}
