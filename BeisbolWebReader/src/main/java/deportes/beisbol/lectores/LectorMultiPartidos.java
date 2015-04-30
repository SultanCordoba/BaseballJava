package deportes.beisbol.lectores;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import deportes.beisbol.json.set1.BeisbolJuegoJson;
import deportes.beisbol.json.set1.Juego;
import deportes.beisbol.model.PartidoBeisbol;
import deportes.beisbol.web.util.EnumReaderActions;

public class LectorMultiPartidos extends BaseballPartidosReader {
	
	private static final Logger logger = LoggerFactory.getLogger(LectorMultiPartidos.class);
	
	private String liga;
	private String grupoLiga;
	
	public String getGrupoLiga() {
		return grupoLiga;
	}

	public void setGrupoLiga(String grupoLiga) {
		this.grupoLiga = grupoLiga;
	}

	public String getLiga() {
		return liga;
	}

	public void setLiga(String liga) {
		this.liga = liga;
	}		

	public LectorMultiPartidos() throws IOException {
		this(LocalDate.now().minusDays(1));
	}
	
	public LectorMultiPartidos(LocalDate fechaJuegos) throws IOException {
		super(fechaJuegos);
		
		this.grupoLiga = this.getPartidoPropiedades().getProperty("grupoLiga");
		this.liga = this.getPartidoPropiedades().getProperty("liga");
	}
		
	public LectorMultiPartidos(String fecha) throws IOException {
		super(fecha);
		
		this.grupoLiga = this.getPartidoPropiedades().getProperty("grupoLiga");
		this.liga = this.getPartidoPropiedades().getProperty("liga");
	}
	
	/* private String construyeLigaPartidos() {
		
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
	} */
	
	/* private PartidoBeisbol construyePartido(HashMap<String, String> datosPartido) {
		PartidoBeisbol resultado = new PartidoBeisbol();
		
		//resultado.setFechaRealizacion(datosPartido.get("fechaJuego"));
		resultado.setClaveMilb(datosPartido.get("minorLeagueId"));
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
	} */
	
	/* private String grabarJuego(PartidoBeisbol partido, String etapa, String vuelta) {
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
	} */
	
	/* private HashMap<String, String> obtenerEquivalenciasEquipos() {
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
	} */
	
	public int obtieneJuegos(EnumReaderActions accion) throws HttpMessageNotReadableException {
        RestTemplate restTemplate = new RestTemplate();
        int resultado = 0;
        
        BeisbolJuegoJson beisbolJuegoJson = new BeisbolJuegoJson();
        try {
			beisbolJuegoJson = restTemplate.getForObject(
					super.construyeLigaPartidos(this.grupoLiga), BeisbolJuegoJson.class);
		} catch (ResourceAccessException rae) {
			logger.info("No hay acceso al sitio para los juegos del dia " + this.getFechaJuegos());
			return 0;
		} catch (HttpClientErrorException ex) {
			logger.info("No hay partidos para este dia " + this.getFechaJuegos());
			return 0;
		} catch (RestClientException e) {
			e.printStackTrace();
			return 0;
		} 
        
        Iterator<Juego> juegos = beisbolJuegoJson.getData().getGames().getGame().iterator();
        Juego juego;
        
        //ArrayList<String> datosJuego;
        //ArrayList<String> extraDatosJuego;
        
        HashMap<String,String> mapaDatosPartido = new HashMap<>();
        HashSet<String> listaEquipos = new HashSet<>();
        
        HashMap<String, String> equivalenciasEquipos = obtenerEquivalenciasEquipos();
        boolean grabar;
        
        while (juegos.hasNext()) {
        	juego = juegos.next();
        	resultado++;
        	
        	grabar = true;
        	
        	if (juego.getLeague().equals(this.getLiga())) {
        		        		
        		//System.out.println("id:" + juego.getId());
        		
        		// System.out.println(construyeLigaJugadores(juego.getId()));
        		
        		if (juego.getStatus().getStatus().equalsIgnoreCase("Final") ||
        				juego.getStatus().getStatus().equalsIgnoreCase("Completed Early")||
        				juego.getStatus().getStatus().equalsIgnoreCase("Game Over")||
        				juego.getStatus().getStatus().equalsIgnoreCase("Suspended")) {        			
        			
        			mapaDatosPartido.clear();
        			
        			try {
        				String estatusJuego = juego.getStatus().getStatus();
        				
        				
	        			if (estatusJuego.equalsIgnoreCase("Completed Early")) {
	        				mapaDatosPartido.put("comentario", "Terminó Temprano.");
	        			}
	        			else {
	        				if (estatusJuego.equalsIgnoreCase("Suspended")) {
	        					mapaDatosPartido.put("comentario", "Suspendido.");
	        				}
	        			}
	            		
	            		ArrayList<String> fechaArreglo = Lists.newArrayList(Splitter.on("/").split(juego.getId()));
	            		mapaDatosPartido.put("minorLeagueId", fechaArreglo.get(fechaArreglo.size() - 1));
	            		fechaArreglo.remove(fechaArreglo.size() - 1);
	            		
	            		mapaDatosPartido.put("entradas", String.valueOf(juego.getStatus().getInning()));
	            		mapaDatosPartido.put("fechaJuego", Joiner.on("-").join(fechaArreglo));
	            		mapaDatosPartido.put("numJuego", juego.getId().substring(juego.getId().length()-1));
	            		
	            		/*System.out.println(mapaDatosPartido.get("fechaJuego"));
	            		
	            		StringBuilder inicioJuego = new StringBuilder(mapaDatosPartido.get("fechaJuego"));
	            		
	            		if (!juego.getId().endsWith("-1")) {
	            			extraDatosJuego.add(String.valueOf(juego.getStatus().getInning()));
	            			extraDatosJuego.add(juego.getId().substring(juego.getId().length()-1));
	            		}
	            		else {            		
		            		if (juego.getStatus().getInning() != 9) {
		            			extraDatosJuego.add(String.valueOf(juego.getStatus().getInning()));
		            		}
	            		}
	            		
	            		if (extraDatosJuego.size() > 0) {
	            			inicioJuego.append("(").append(Joiner.on("-").join(extraDatosJuego)).append(")");
	            		}*/
	            		
	            		mapaDatosPartido.put("eqVisita", juego.getAway_name_abbrev());
	            		mapaDatosPartido.put("carVisita", String.valueOf(juego.getLinescore().getR().getAway()));
	            		mapaDatosPartido.put("hitsVisita", String.valueOf(juego.getLinescore().getH().getAway()));
	            		mapaDatosPartido.put("errorVisita", String.valueOf(juego.getLinescore().getE().getAway()));
	            		mapaDatosPartido.put("carLocal", String.valueOf(juego.getLinescore().getR().getHome()));
	            		mapaDatosPartido.put("eqLocal", juego.getHome_name_abbrev());
	            		mapaDatosPartido.put("hitsLocal", String.valueOf(juego.getLinescore().getH().getHome()));
	            		mapaDatosPartido.put("errorLocal", String.valueOf(juego.getLinescore().getE().getHome()));
        			} catch (Exception e) {
        				grabar = false;
        				logger.info("Error:" + e.getMessage());
        			}
            		/*datosJuego.add(inicioJuego.toString());
            		datosJuego.add(juego.getAway_name_abbrev());
            		datosJuego.add(String.valueOf(juego.getLinescore().getR().getAway()));
            		datosJuego.add(String.valueOf(juego.getLinescore().getR().getHome()));
            		datosJuego.add(juego.getHome_name_abbrev());*/            		
            		
            		//System.out.println(Joiner.on(",").join(datosJuego));
            		
        			if (grabar) {
	            		switch(accion) {
	            		case GUARDAR_PARTIDOS:            			
	            			if (equivalenciasEquipos.containsKey(mapaDatosPartido.get("eqVisita"))) {
	            				mapaDatosPartido.put("eqVisita", equivalenciasEquipos.get(mapaDatosPartido.get("eqVisita")));
	            			}
	
	            			if (equivalenciasEquipos.containsKey(mapaDatosPartido.get("eqLocal"))) {
	            				mapaDatosPartido.put("eqLocal", equivalenciasEquipos.get(mapaDatosPartido.get("eqLocal")));
	            			}
	            			
	            	        /* Properties partidoPropiedades = new Properties();
	            			String rutaPropiedades = System.getProperty("user.dir") + 
	            					File.separator + "lector.properties";        
	            			
	            			try {
	            				partidoPropiedades.load(new FileInputStream(rutaPropiedades));
	            			} catch (IOException e) {
	            				e.printStackTrace();			
	            			} */
	            			            			
	            			PartidoBeisbol partidoPaso = super.construyePartido(mapaDatosPartido);
	            			
	            			logger.info(super.grabarJuego(partidoPaso, this.getPartidoPropiedades().getProperty("etapa"), 
	            					this.getPartidoPropiedades().getProperty("vuelta")));
	            			// logger.info(partidoPaso.partidoString());
	            			break;
	            			
	            		case OBTENER_EQUIPOS:
	            			listaEquipos.add(mapaDatosPartido.get("eqVisita"));
	            			listaEquipos.add(mapaDatosPartido.get("eqLocal"));
	            			break;
	            		}
        			}
        		}
        		else {
        			logger.info("status:" + juego.getStatus().getStatus());
        		}        	        	
        	}
        }
        
        if (listaEquipos.size() > 0) {
        	logger.info(listaEquipos.toString());
        }
        
        return resultado; 
	}
}
