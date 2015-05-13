package deportes.beisbol.web.controller;

import java.util.Iterator;
import java.util.LinkedHashSet;
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

import deportes.beisbol.converter.EquipoConverter;
import deportes.beisbol.converter.ParticipanteConverter;
import deportes.beisbol.jpa.model.Equipo;
import deportes.beisbol.jpa.model.Participante;
import deportes.beisbol.jpa.services.ParticipanteService;
import deportes.beisbol.model.EquipoBeisbol;
import deportes.beisbol.utils.ConstructorBreadcrumb;

@Controller
@RequestMapping("/equipo")
public class EquipoController {
	
	private static final Logger logger = LoggerFactory.getLogger(EquipoController.class);
	
	@Autowired
	ParticipanteService participanteService;

	@RequestMapping(value = "{id}/show/{zona}", method = RequestMethod.GET)
	public String getEquipo(@PathVariable short id, @PathVariable String zona, 
			Model model, Locale locale) {
		
		Participante participante = participanteService.findOne(id).get();
		
		// logger.info("Participante:" + participante.getId() + " Equipos: "  + participante.getEquipos().size());
		
		LinkedHashSet<EquipoBeisbol> equipos = new LinkedHashSet<>();
		Iterator<Equipo> iteraEquipos = participante.getEquipos().iterator();
		
		while (iteraEquipos.hasNext()) {
			equipos.add(EquipoConverter.convierteDeBase(iteraEquipos.next()));
		}
		
		model.addAttribute("equipos", equipos);
		
		model.addAttribute("menuBread", ConstructorBreadcrumb.construyeEquipo(participante, zona));
		
		String menuActivo = "INICIO";
		
		switch (zona.toUpperCase()) {
		case "EQUIPOS":
			menuActivo = participante.getTemporada().getNombre();
			break;
			
		case "TEMPORADAS":
			menuActivo = ParticipanteConverter.nombreParticipante(participante, false, Optional.of(locale.getLanguage()));
			break;
		}
		
		model.addAttribute("menuActivo", menuActivo);
		
		return "../templates/equipo/show";
	}
}
