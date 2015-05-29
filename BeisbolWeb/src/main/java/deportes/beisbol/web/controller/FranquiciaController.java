package deportes.beisbol.web.controller;

import java.util.Iterator;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import deportes.beisbol.jpa.model.Liga;
import deportes.beisbol.jpa.model.LigaHistorico;
import deportes.beisbol.jpa.services.FranquiciaService;
import deportes.beisbol.jpa.services.LigaService;
import deportes.beisbol.utils.ConstructorBreadcrumb;
import deportes.beisbol.web.model.FranquiciaModel;

@Controller
@RequestMapping("/franquicia")
public class FranquiciaController {
	
	@Autowired
	FranquiciaService franquiciaService;
	
	@Autowired
	LigaService ligaService;
		
	@RequestMapping(value = "{id}/show", method = RequestMethod.GET)
	public String showFranquicia(@PathVariable Short id, Model model, Locale locale) {
		
		FranquiciaModel franquiciaModelo = franquiciaService.creaFranquiciaModelo(id, Optional.of(locale.getLanguage()));
		
		Liga ligaPaso = ligaService.findOneBd(franquiciaModelo.getLiga().getId()).get();
		
		Iterator<LigaHistorico> ligaHistoricos = ligaPaso.getLigaHistoricos().iterator();
		
		model.addAttribute("menuBread", ConstructorBreadcrumb.construyeLiga(ligaHistoricos.next(), "equipos"));
		model.addAttribute("menuActivo", franquiciaModelo.getFranquicia().getNombre());
		model.addAttribute("modelo", franquiciaModelo);
		
		return "../templates/franquicia/show";
	}

}
