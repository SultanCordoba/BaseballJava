package deportes.beisbol.web.controller.restful;

import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import deportes.beisbol.jpa.services.JugadorService;
import deportes.beisbol.model.JugadorBeisbol;

@RestController
@RequestMapping("/jugador-restful")
public class JugadorRestfulController {
	
	@Autowired
	JugadorService jugadorService;
	
	@RequestMapping(value = "/search/{nombre}", method = RequestMethod.GET)
	@ResponseBody 
	public LinkedHashSet<JugadorBeisbol> searchJugadores(@PathVariable String nombre) {
		
		LinkedHashSet<JugadorBeisbol> resultado = (LinkedHashSet<JugadorBeisbol>) jugadorService.findLikeNombre(nombre);
		
		return resultado;
	}
}
