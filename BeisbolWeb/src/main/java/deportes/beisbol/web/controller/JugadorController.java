package deportes.beisbol.web.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import deportes.beisbol.jpa.services.JugadorService;
import deportes.beisbol.utils.ConstructorBreadcrumb;
import deportes.beisbol.web.model.JugadorModel;

@Controller
@RequestMapping("/jugador")
public class JugadorController {
	
	//private static final Logger logger = LoggerFactory.getLogger(JugadorController.class);
	
	@Autowired
	JugadorService jugadorService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchJugadores(Model model, Locale locale) {
		
		String jugadoresTitulo = "Jugadores";
		switch (locale.getLanguage().toUpperCase()) {
		case "EN":
			jugadoresTitulo = "Players";
			break;
		}
		
		model.addAttribute("menuBread", ConstructorBreadcrumb.construyeInicio());
		model.addAttribute("menuActivo", jugadoresTitulo);
		
		return "../templates/jugador/search";
	}
	
	@RequestMapping(value = "/{id}/show/{zona}", method = RequestMethod.GET)
	public String getJugador(@PathVariable Short id, @PathVariable String zona, 
			Model model, Locale locale) {
		
		JugadorModel jugadorModelo = jugadorService.getJugador(id);
		
		model.addAttribute("menuBread", ConstructorBreadcrumb.construyeJugador());
		model.addAttribute("menuActivo", jugadorModelo.getJugador().getNombreAbreviado());
		model.addAttribute("modelo", jugadorModelo);
		
		/* switch(zona) {
		case "J":
		} */
		
		return "../templates/jugador/show";
	}
	
}
