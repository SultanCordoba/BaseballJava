package deportes.beisbol.web.controller;

import java.util.Iterator;
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
import deportes.beisbol.converter.EtapaConverter;
import deportes.beisbol.converter.LigaConverter;
import deportes.beisbol.jpa.model.Temporada;
import deportes.beisbol.jpa.services.TemporadaService;
import deportes.beisbol.model.EquipoBeisbol;
import deportes.beisbol.model.EtapaBeisbol;
import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.model.TemporadaBeisbol;
import deportes.beisbol.service.EquipoService;
import deportes.beisbol.service.EtapaService;
import deportes.beisbol.service.LigaService;
import deportes.beisbol.utils.EtapaBeisbolAux;

@Controller
@RequestMapping("/temporada")
public class TemporadaController {

	@Autowired
	LigaService ligaService;
	
	@Autowired
	TemporadaService temporadaService;
	
	@Autowired
	EquipoService equipoService;
	
	@Autowired
	EtapaService etapaService;
	
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET) 
	public String getTemporada(@PathVariable Short id, Model model, Locale locale) {
		Optional<TemporadaBeisbol> resultado;
		
		// resultado = temporadaService.findByNombreAndLiga(nombre, siglas);
		
		resultado = temporadaService.findById(id);
		
		resultado.orElseThrow(() -> new TemporadaNotFoundException(id));
		
		Optional<String> idioma = Optional.of(locale.getLanguage());
		
		if (resultado.isPresent()) {
			
			resultado.get().setEtapas(etapaService.findEtapasByTemporada(resultado.get(), idioma));
			
			Optional<EquipoBeisbol> campeon = equipoService.findCampeon(resultado.get());
			
			if (campeon.isPresent()) {
				resultado.get().setCampeon(campeon.get());
			}
		}
		
		// return resultado.get();
		
		model.addAttribute("temporada", resultado.get());
		
		//Optional<LigaBeisbol> ligaBeisbol = temporadaService.findLigaPorTemporada(resultado.get().getId(), idioma);
		
		Temporada temporada = temporadaService.findOneBd(resultado.get().getId()).get();
		LigaBeisbol ligaBeisbol = LigaConverter.convierteDeBase
				(temporada.getLigaHistorico(), idioma);
				
		model.addAttribute("liga", ligaBeisbol);
		
		LinkedHashSet<EtapaBeisbolAux> etapaVista = new LinkedHashSet<>();
		Iterator<EtapaBeisbol> iteraEtapas = (Iterator<EtapaBeisbol>) resultado.get().getEtapas().iterator();
		EtapaBeisbol etapa;
		
		while (iteraEtapas.hasNext()) {
			etapa = iteraEtapas.next();
			
			etapaVista.add(EtapaConverter.convierteDeEntidad(etapa));
		}
		
		model.addAttribute("etapas", etapaVista);
		
		return "../templates/temporada/show";
	}
	
	@ExceptionHandler(TemporadaNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo handleTemporadaNotFoundException(HttpServletRequest req, 
			TemporadaNotFoundException ex) {
		// Locale locale = LocaleContextHolder.getLocale();
		// String errorMessage = messageSource.getMessage("error.bad.smartphone.id", null, locale);
		String errorMessage = ex.getMessage();

		//errorMessage += ex.getValue();
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
