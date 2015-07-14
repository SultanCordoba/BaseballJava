package deportes.beisbol.web.controller;

import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import deportes.beisbol.ErrorInfo;
import deportes.beisbol.jpa.model.Temporada;
import deportes.beisbol.jpa.services.PartidoService;
import deportes.beisbol.jpa.services.TemporadaService;
import deportes.beisbol.model.PartidoBeisbol;
import deportes.beisbol.utils.ConstructorBreadcrumb;
import deportes.beisbol.web.model.TemporadaModel;

@Controller
@RequestMapping("/temporada")
public class TemporadaController {

	@Autowired
	TemporadaService temporadaService;
	
	@Autowired
	PartidoService partidoService;
	
	@RequestMapping(value = "/{id}/show", method = RequestMethod.GET) 
	public String getTemporada(@PathVariable Short id, Model model, Locale locale) {		
		Optional<Temporada> temporada = temporadaService.findOneBd(id);
		
		TemporadaModel temporadaModelo = temporadaService.crearTemporadaModel(id, Optional.of(locale.getLanguage()));
		
		temporadaModelo.setPartidos((LinkedHashSet<PartidoBeisbol>) 
				partidoService.findByTemporada(id, Optional.of(locale.getLanguage())));
		
		model.addAttribute("menuBread", ConstructorBreadcrumb.construyeLiga(temporada.get().getLigaHistorico(), "temporadas"));
		model.addAttribute("menuActivo", temporadaModelo.getTemporada().getNombre());
		model.addAttribute("modelo", temporadaModelo);
		
		return "../templates/temporada/show";
	}
	
	@ExceptionHandler(TemporadaNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo handleTemporadaNotFoundException(HttpServletRequest req, 
			TemporadaNotFoundException ex) {
		String errorMessage = ex.getMessage();
		String errorURL = req.getRequestURL().toString();

		return new ErrorInfo(errorURL, errorMessage);
	}
}

//@ResponseStatus(HttpStatus.NOT_FOUND)
class TemporadaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6779250917217620051L;

	public TemporadaNotFoundException(Short id) {
		super("No hay temporada '" + id + ".");
	}
}
