package deportes.beisbol.web.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/equipo")
public class EquipoController {

	@RequestMapping(value = "{id}/show/{zona}", method = RequestMethod.GET)
	public String getEquipo(@PathVariable short id, @PathVariable String zona, 
			Model model, Locale locale) {
		
		model.addAttribute("menuActivo", "inicio");
		
		return "../templates/equipo/show";
	}
}
