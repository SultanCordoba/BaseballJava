package deportes.beisbol.web.controller;

import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import deportes.beisbol.converter.ParticipanteConverter;
import deportes.beisbol.jpa.services.EquipoService;
import deportes.beisbol.utils.ConstructorBreadcrumb;
import deportes.beisbol.web.model.EquipoModel;

@Controller
@RequestMapping("/equipo")
public class EquipoController {
	
	private static final Logger logger = LoggerFactory.getLogger(EquipoController.class);
	
	@Autowired
	EquipoService equipoService;

	@RequestMapping(value = "{id}/show/{zona}", method = RequestMethod.GET)
	public String getEquipo(@PathVariable short id, @PathVariable String zona, 
			Model model, Locale locale) {

		// logger.info("Region = " + locale.getCountry() + " Display = " + locale.getScript());
		
		EquipoModel equipoModelo = equipoService.creaEquipoModel(id, Optional.of(locale.getLanguage()));
		Optional<String> idioma = Optional.of(locale.getLanguage());
				
		model.addAttribute("menuBread", ConstructorBreadcrumb.construyeEquipo(equipoModelo.getParticipante(), zona, idioma));
		
		String menuActivo = "INICIO";
		
		switch (zona.toUpperCase()) {
		case "E":
			menuActivo = equipoModelo.getParticipante().getTemporada().getNombre();
			break;
			
		case "T":
			menuActivo = ParticipanteConverter.nombreParticipante(equipoModelo.getParticipante(), false, idioma);
			break;
		}
		
		model.addAttribute("menuActivo", menuActivo);
		model.addAttribute("equipos", equipoModelo.getEquipos());
		
		return "../templates/equipo/show";
	}
}
