package deportes.beisbol.web.controller;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.service.FranquiciaService;

@Controller
@RequestMapping("/franquicia")
public class FranquiciaController {
	
	@Autowired
	FranquiciaService franquiciaService;
	
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String showFranquicia(@PathVariable Short id, Model model, Locale locale) {
		
		Optional<FranquiciaBeisbol> resultado;
		
		Optional<String> idioma = Optional.of(locale.getLanguage());
		
		resultado = franquiciaService.findById(id);
		
		model.addAttribute("franquicia", resultado.get());
		model.addAttribute("pais", resultado.get().getPais());
		
		return "../templates/franquicia/show";
	}

}
