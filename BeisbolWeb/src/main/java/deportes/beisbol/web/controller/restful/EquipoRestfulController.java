package deportes.beisbol.web.controller.restful;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;

import deportes.beisbol.jpa.services.EquipoService;
import deportes.beisbol.model.EquipoBeisbol;
import deportes.beisbol.utils.EquipoPais;
import deportes.beisbol.utils.PaginaDefinidor;
import deportes.beisbol.utils.RespuestaDataTables;
import deportes.beisbol.web.model.EquipoModel;

@RestController
@RequestMapping("/equipo-restful")
public class EquipoRestfulController {

	@Autowired
	EquipoService equipoService;
	
	@RequestMapping(value = "{id}/show", method = RequestMethod.GET)
	public EquipoModel getEquipo(@PathVariable short id, Locale locale) {
		EquipoModel equipoModelo = equipoService.creaEquipoModel(id, Optional.of(locale.getLanguage()));
		equipoModelo.setParticipante(null);
		
		return equipoModelo;
	}
	
	@RequestMapping(value = "/search/{siglas}", method = RequestMethod.GET)
	@ResponseBody
	public RespuestaDataTables searchEquipos(@PathVariable String siglasLiga, 
			HttpServletRequest request, Locale locale) {
		String busqueda = request.getParameter("search[value]");
		
	    if (Strings.isNullOrEmpty(busqueda)) busqueda="";
		
	    PaginaDefinidor pagina = new PaginaDefinidor();
	    
	    pagina.setInicio(Integer.valueOf(request.getParameter("start")).intValue());
	    pagina.setLongitud(Integer.valueOf(request.getParameter("length")).intValue());
	    
		RespuestaDataTables resultado = new RespuestaDataTables();
		
		LinkedHashMap<String, EquipoPais> equipos = equipoService.busqueda
				(siglasLiga, busqueda, pagina, Optional.of(locale.getLanguage()));
		
		EquipoPais equipoAux = null;
		EquipoBeisbol paso = null;
		
		LinkedHashSet<ArrayList<String>> equiposJson = new LinkedHashSet<>();
		ArrayList<String> datosEquipo = new ArrayList<>();
		
		for (String nombreEquipo : equipos.keySet()) {
			datosEquipo = new ArrayList<>();
			datosEquipo.add(equipos.get(nombreEquipo).getNombre());
			datosEquipo.add(equipos.get(nombreEquipo).getPais());
		}
		
		resultado.setDraw(Integer.valueOf(request.getParameter("draw")) + 1);
		
		/* resultado.setRecordsFiltered(totalFiltrados);
		resultado.setRecordsTotal(totalFiltrados); 
		resultado.setData(jugadoresJson); */
		
		return resultado;
	}
	
}
