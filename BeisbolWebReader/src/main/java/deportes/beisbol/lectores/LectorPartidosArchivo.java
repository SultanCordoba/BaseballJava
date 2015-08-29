package deportes.beisbol.lectores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/* import org.slf4j.Logger;
import org.slf4j.LoggerFactory; */

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import deportes.beisbol.model.PartidoBeisbol;

public class LectorPartidosArchivo extends BaseballPartidosReader {

	//private static final Logger logger = LoggerFactory.getLogger(LectorPartidosArchivo.class);
	
	private HashMap<String, String> traducePartido(String partido) {
		HashMap<String, String> resultado = new HashMap<>();
		
		resultado.put("entradas", "9");
		
		System.out.println("Entradas es " + resultado.get("entradas"));
		
		resultado.put("numJuego", "1");
		// resultado.put("minorLeagueId", "");
		
		ArrayList<String> datosPartido = Lists.newArrayList(Splitter.on(",").trimResults().omitEmptyStrings().split(partido));
		//String fechaPartido = datosPartido.get(0);
		
		/* if (fechaJuego.contains("(")) {
			
		} */
		
		resultado.put("fechaJuego", datosPartido.get(0));
		resultado.put("eqVisita", datosPartido.get(1));
		resultado.put("carVisita", datosPartido.get(2));
		resultado.put("eqLocal",  datosPartido.get(3));
		resultado.put("carLocal", datosPartido.get(4));
		
		if (datosPartido.size() > 5) {
			resultado.put("comentario", datosPartido.get(5));
		}
		
		resultado.put("hitsVisita", "0");
		resultado.put("errorVisita", "0");
		resultado.put("hitsLocal", "0");
		resultado.put("errorLocal", "0");
		
		return resultado;	
	}
	
	public int obtieneJuegos() {
		
		int resultado = 0;
		
		/* Properties partidoPropiedades = new Properties();
		String rutaPropiedades = System.getProperty("user.dir") + 
				File.separator + "lector.properties";        
		
		try {
			partidoPropiedades.load(new FileInputStream(rutaPropiedades));
		} catch (IOException e) {
			e.printStackTrace();			
		} */
		
		String rutaPartidos = System.getProperty("user.dir") + 
				File.separator + "partidos.txt";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(rutaPartidos));
			
			String line;
		    while ((line = br.readLine()) != null) {
		    	PartidoBeisbol partidoPaso = super.construyePartido(traducePartido(line));
		    	
		    	resultado++;
		    	
		        System.out.println(super.grabarJuego(partidoPaso, this.getPartidoPropiedades().getProperty("etapa"), 
		        		this.getPartidoPropiedades().getProperty("vuelta")));
		    }
		    br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public LectorPartidosArchivo() throws IOException {
		super();
	}
}
