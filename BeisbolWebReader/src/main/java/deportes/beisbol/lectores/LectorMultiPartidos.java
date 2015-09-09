package deportes.beisbol.lectores;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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
	            		
	            		String strFechaArreglo = fechaArreglo.stream()
	            				.filter(p -> p != null)
	            				.collect(Collectors.joining("-"));
	            		
	            		mapaDatosPartido.put("fechaJuego", strFechaArreglo);
	            		mapaDatosPartido.put("numJuego", juego.getId().substring(juego.getId().length()-1));
	            			            		
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
            		            		
        			if (grabar) {
	            		switch(accion) {
	            		case GUARDAR_PARTIDOS:            			
	            			if (equivalenciasEquipos.containsKey(mapaDatosPartido.get("eqVisita"))) {
	            				mapaDatosPartido.put("eqVisita", equivalenciasEquipos.get(mapaDatosPartido.get("eqVisita")));
	            			}
	
	            			if (equivalenciasEquipos.containsKey(mapaDatosPartido.get("eqLocal"))) {
	            				mapaDatosPartido.put("eqLocal", equivalenciasEquipos.get(mapaDatosPartido.get("eqLocal")));
	            			}
	            			
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
