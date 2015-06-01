package deportes.beisbol.web.controller.restful;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import deportes.beisbol.jpa.services.JugadorService;
import deportes.beisbol.model.JugadorBeisbol;
import deportes.beisbol.utils.PaginaDefinidor;
import deportes.beisbol.utils.RespuestaDataTables;
import deportes.beisbol.web.model.JugadorModel;

@RestController
@RequestMapping("/jugador-restful")
public class JugadorRestfulController {
	
	private static final Logger logger = LoggerFactory.getLogger(JugadorRestfulController.class);
	
	@Autowired
	JugadorService jugadorService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody 
	public RespuestaDataTables searchJugadores(HttpServletRequest request, Locale locale) {		
		// logger.info(request.getParameterMap().keySet().toString());
	    
	    String busqueda = request.getParameter("search[value]");
	    
	    if (Strings.isNullOrEmpty(busqueda)) busqueda="";
		
	    PaginaDefinidor pagina = new PaginaDefinidor();
	    
	    pagina.setInicio(Integer.valueOf(request.getParameter("start")).intValue());
	    pagina.setLongitud(Integer.valueOf(request.getParameter("length")).intValue());
	    
		RespuestaDataTables resultado = new RespuestaDataTables();
		
		Iterator<JugadorBeisbol> jugadores = jugadorService.search(busqueda, pagina).iterator();
		JugadorBeisbol paso;
		
		LinkedHashSet<ArrayList<String>> jugadoresJson = new LinkedHashSet<>();
		ArrayList<String> datosJugador;
		
		Joiner joiner1 = Joiner.on("/").skipNulls();
		ArrayList<String> rutaJugador = new ArrayList<>();
		
		rutaJugador.add(request.getContextPath());
		rutaJugador.add("jugador");
		rutaJugador.add("0");
		rutaJugador.add("show");
		rutaJugador.add("J");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		
		switch (locale.getLanguage().toUpperCase()) {
		case "EN":
			formatter = formatter.withLocale(locale);
		}
		
		while (jugadores.hasNext()) {
			paso = jugadores.next();
			rutaJugador.set(2, Short.toString(paso.getId()));

			datosJugador = new ArrayList<>();
			datosJugador.add("<a href=\"" + joiner1.join(rutaJugador) + "\">" +
				paso.getNombreCompleto() + "</a>");
			
			if (paso.getFechaNacimiento() != null) {
				datosJugador.add(paso.getFechaNacimiento().format(formatter).toUpperCase());
			}
			else {
				datosJugador.add("");
			}
			datosJugador.add(paso.getCiudadNacimiento());
			
			jugadoresJson.add(datosJugador);
		}
		
		resultado.setDraw(Integer.valueOf(request.getParameter("draw")) + 1);
		
		int totalFiltrados = jugadorService.totalRegistros(busqueda);
		
		resultado.setRecordsFiltered(totalFiltrados);
		resultado.setRecordsTotal(totalFiltrados); 
		resultado.setData(jugadoresJson);
		
		return resultado;
	}
	
	@RequestMapping(value = "/{id}/show", method = RequestMethod.GET)
	@ResponseBody 
	public JugadorModel getJugador(@PathVariable short id) {
		return jugadorService.getJugador(id);
	}
}
