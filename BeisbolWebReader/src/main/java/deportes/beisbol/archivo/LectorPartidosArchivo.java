package deportes.beisbol.archivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import deportes.beisbol.model.ContrincanteBeisbol;
import deportes.beisbol.model.EquipoBeisbol;
import deportes.beisbol.model.PartidoBeisbol;

public class LectorPartidosArchivo {

	private static final Logger logger = LoggerFactory.getLogger(LectorPartidosArchivo.class);
	
	private String grabarJuego(PartidoBeisbol partido, String etapa, String vuelta) {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());  
	    
		//restTemplate.setErrorHandler(new MyResponseErrorHandler());
				
		String url = "http://localhost:8090/baseball/partido/guardar/" + etapa + "/" + vuelta;
		
		try {
			ResponseEntity<String> regreso = restTemplate.postForEntity
					(url, partido, String.class); 
			
			if (regreso.getStatusCode() != HttpStatus.OK) {
				return "Estatus error es " + regreso.getStatusCode().toString();
			}
			else {
				return regreso.getBody();
			}
			
		} catch (HttpClientErrorException hcee) {
			hcee.printStackTrace();
			System.out.println(hcee.getResponseBodyAsString());
			return hcee.getMessage();
		}
	}
	
	private PartidoBeisbol construyePartido(HashMap<String, String> datosPartido) {
		PartidoBeisbol resultado = new PartidoBeisbol();
		
		//resultado.setFechaRealizacion(datosPartido.get("fechaJuego"));
		if (datosPartido.containsKey("minorLeagueId")) {
			resultado.setClaveMilb(datosPartido.get("minorLeagueId"));
		}
		resultado.setFechaRealizacion(LocalDate.parse(datosPartido.get("fechaJuego"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		resultado.setEntradas(Integer.parseInt(datosPartido.get("entradas")));
		resultado.setNumJuego(Integer.parseInt(datosPartido.get("numJuego")));
		
		if (datosPartido.containsKey("comentario")) {
			resultado.setComentario(datosPartido.get("comentario"));
		}
		
		ContrincanteBeisbol cteVisitante = new ContrincanteBeisbol();
		EquipoBeisbol eqVisita = new EquipoBeisbol();
		eqVisita.setSiglas(datosPartido.get("eqVisita"));
		cteVisitante.setEquipo(eqVisita);
		cteVisitante.setScore(Short.parseShort(datosPartido.get("carVisita")));	
		cteVisitante.setHits(Byte.parseByte(datosPartido.get("hitsVisita")));
		cteVisitante.setErrores(Byte.parseByte(datosPartido.get("errorVisita")));
		resultado.setVisita(cteVisitante);
		
		ContrincanteBeisbol cteLocal = new ContrincanteBeisbol();
		EquipoBeisbol eqLocal = new EquipoBeisbol();
		eqLocal.setSiglas(datosPartido.get("eqLocal"));
		cteLocal.setEquipo(eqLocal);
		cteLocal.setScore(Short.parseShort(datosPartido.get("carLocal")));
		cteLocal.setHits(Byte.parseByte(datosPartido.get("hitsLocal")));
		cteLocal.setErrores(Byte.parseByte(datosPartido.get("errorLocal")));
		
		resultado.setLocal(cteLocal);
		
		return resultado;
	}
	
	private HashMap<String, String> traducePartido(String partido) {
		HashMap<String, String> resultado = new HashMap<>();
		
		resultado.put("entradas", "9");
		
		System.out.println("Entradas es " + resultado.get("entradas"));
		
		resultado.put("numJuego", "1");
		// resultado.put("minorLeagueId", "");
		
		ArrayList<String> datosPartido = Lists.newArrayList(Splitter.on(",").trimResults().omitEmptyStrings().split(partido));
		String fechaJuego = datosPartido.get(0);
		
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
	
	public int ObtenerPartidos() {
		
		int resultado = 0;
		
		Properties partidoPropiedades = new Properties();
		String rutaPropiedades = System.getProperty("user.dir") + 
				File.separator + "lector.properties";        
		
		try {
			partidoPropiedades.load(new FileInputStream(rutaPropiedades));
		} catch (IOException e) {
			e.printStackTrace();			
		}
		
		String etapa = partidoPropiedades.getProperty("etapa");
		String vuelta = partidoPropiedades.getProperty("vuelta");
		
		String rutaPartidos = System.getProperty("user.dir") + 
				File.separator + "partidos.txt";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(rutaPartidos));
			
			String line;
		    while ((line = br.readLine()) != null) {
		    	PartidoBeisbol partidoPaso = construyePartido(traducePartido(line));
		        System.out.println(grabarJuego(partidoPaso, etapa, vuelta));
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
}
