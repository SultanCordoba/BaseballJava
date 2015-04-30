package deportes.beisbol.lectores;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Joiner;

import deportes.beisbol.model.ContrincanteBeisbol;
import deportes.beisbol.model.EquipoBeisbol;
import deportes.beisbol.model.PartidoBeisbol;

public class BaseballPartidosReader {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseballPartidosReader.class);
	
	public static final String formatoFecha = "yyyy-MM-dd";
	
	private LocalDate fechaJuegos;
	
	private Properties partidoPropiedades;
	
	public LocalDate getFechaJuegos() {
		return fechaJuegos;
	}

	public void setFechaJuegos(LocalDate fechaJuegos) {
		this.fechaJuegos = fechaJuegos;
	}
	
	public void setFechaJuegos(String fecha) {
		this.fechaJuegos = LocalDate.parse(fecha, DateTimeFormatter.ofPattern(formatoFecha));
	}
	
	public Properties getPartidoPropiedades() {
		return this.partidoPropiedades;
	}
		
	public String construyeLigaPartidos(String grupoLiga) {
		
		Joiner joiner = Joiner.on("/");
		
		ArrayList<String> sitioPartidos = new ArrayList<>();
		
		sitioPartidos.add("http:/");
		
		String host = "www.milb.com/gdcross/components/game";
		
		sitioPartidos.add(host);
		sitioPartidos.add(grupoLiga);
		sitioPartidos.add("year_" + fechaJuegos.getYear());
		sitioPartidos.add("month_" + String.format("%02d", fechaJuegos.getMonthValue()));
		sitioPartidos.add("day_" + String.format("%02d", fechaJuegos.getDayOfMonth()));
		sitioPartidos.add("master_scoreboard.json");
		
		logger.info("Liga de partidos:" + joiner.join(sitioPartidos));
		
		return joiner.join(sitioPartidos);
	}
	
	public String grabarJuego(PartidoBeisbol partido, String etapa, String vuelta) {
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
	
	public PartidoBeisbol construyePartido(HashMap<String, String> datosPartido) {
		PartidoBeisbol resultado = new PartidoBeisbol();
		
		if (datosPartido.containsKey("minorLeagueId")) {
			resultado.setClaveMilb(datosPartido.get("minorLeagueId"));
		}
		resultado.setFechaRealizacion(LocalDate.parse(datosPartido.get("fechaJuego"), DateTimeFormatter.ofPattern(formatoFecha)));
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
	
	public String construyeLigaJugadores(String juegoId, String grupoLiga) {
		
		Joiner joiner = Joiner.on("/");
		
		ArrayList<String> sitioPartidos = new ArrayList<>();
		
		sitioPartidos.add("http:/");
		
		String host = "origin.milb.com/gdcross/components/game";
		
		String rutaDef = "gid_" + juegoId.replace("/", "_").replace("-", "_");
		
		sitioPartidos.add(host);
		sitioPartidos.add(grupoLiga);
		sitioPartidos.add("year_" + fechaJuegos.getYear());
		sitioPartidos.add("month_" + String.format("%02d", fechaJuegos.getMonthValue()));
		sitioPartidos.add("day_" + String.format("%02d", fechaJuegos.getDayOfMonth()));
		sitioPartidos.add(rutaDef);
		sitioPartidos.add("boxscore.json");
		
		//System.out.println(joiner.join(sitioPartidos));
		
		return joiner.join(sitioPartidos);
	}
	
	public HashMap<String, String> obtenerEquivalenciasEquipos() {
		HashMap<String, String> resultado = new HashMap<>();
		
        Properties archivoEquivalencias = new Properties();
		String rutaPropiedades = System.getProperty("user.dir") + 
				File.separator + "equivalencias.properties";        
		
		try {
			archivoEquivalencias.load(new FileInputStream(rutaPropiedades));
		} catch (IOException e) {
			e.printStackTrace();			
		}
		
		if (!archivoEquivalencias.isEmpty()) {
			Iterator<Object> iteradorLlaves = archivoEquivalencias.keySet().iterator();
			
			String llave = "";
			while (iteradorLlaves.hasNext()) {
				llave = iteradorLlaves.next().toString();
				resultado.put(llave, archivoEquivalencias.getProperty(llave));
			}
		}
				
		return resultado;
	}
	
	public BaseballPartidosReader() throws IOException {		
		String rutaPropiedades = System.getProperty("user.dir") + 
				File.separator + "lector.properties";        
		
		partidoPropiedades = new Properties();
		partidoPropiedades.load(new FileInputStream(rutaPropiedades));
	}
	
	public BaseballPartidosReader(String fecha) throws IOException {
		this(LocalDate.parse(fecha, DateTimeFormatter.ofPattern(formatoFecha)));
	}
	
	public BaseballPartidosReader(LocalDate fechaJuegos) throws IOException {
		this();
		this.fechaJuegos = fechaJuegos;
	}
}
