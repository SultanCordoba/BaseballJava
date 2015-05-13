package deportes.beisbol.web.controller;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.TreeMap;

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
import deportes.beisbol.converter.TemporadaConverter;
import deportes.beisbol.jpa.model.Temporada;
import deportes.beisbol.jpa.services.LigaService;
import deportes.beisbol.jpa.services.TemporadaService;
import deportes.beisbol.model.EquipoBeisbol;
import deportes.beisbol.model.EtapaBeisbol;
import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.model.TemporadaBeisbol;
import deportes.beisbol.service.EquipoService;
import deportes.beisbol.service.EtapaService;
import deportes.beisbol.utils.ConstructorBreadcrumb;
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
	
	@RequestMapping(value = "/{id}/show", method = RequestMethod.GET) 
	public String getTemporada(@PathVariable Short id, Model model, Locale locale) {
		Optional<TemporadaBeisbol> resultado = Optional.empty();
		
		/* resultado = temporadaService.findById(id);
		
		resultado.orElseThrow(() -> new TemporadaNotFoundException(id)); */
		
		Optional<Temporada> temporada = temporadaService.findOneBd(id);
		
		if (temporada.isPresent()) {
			resultado = Optional.ofNullable(TemporadaConverter.convierteDeBase(temporada.get()));
		}
		
		Optional<String> idioma = Optional.of(locale.getLanguage());
		
		if (resultado.isPresent()) {
			
			resultado.get().setEtapas(etapaService.findEtapasByTemporada(resultado.get(), idioma));
			
			Optional<EquipoBeisbol> campeon = equipoService.findCampeon(resultado.get());
			
			if (campeon.isPresent()) {
				resultado.get().setCampeon(campeon.get());
			}
		}
		
		model.addAttribute("temporada", resultado.get());
		
		LigaBeisbol ligaBeisbol = LigaConverter.convierteDeBase(temporada.get().getLigaHistorico(), idioma);
		
		//Optional<LigaBeisbol> ligaBeisbol = temporadaService.findLigaPorTemporada(resultado.get().getId(), idioma);
		//Optional<LigaBeisbol> ligaBeisbol = ligaService.findOne(idLiga, idioma);
		
		//model.addAttribute("liga", ligaBeisbol);

		/* HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		TreeMap<String, String> auxiliar = new TreeMap<>();
		auxiliar.put("ligaSiglas", ligaBeisbol.getSiglas());
		auxiliar.put("zonaLiga", "temporadas"); */		
		
		LinkedHashSet<EtapaBeisbolAux> etapaVista = new LinkedHashSet<>();
		Iterator<EtapaBeisbol> iteraEtapas = (Iterator<EtapaBeisbol>) resultado.get().getEtapas().iterator();
		EtapaBeisbol etapa;
		
		while (iteraEtapas.hasNext()) {
			etapa = iteraEtapas.next();
			
			etapaVista.add(EtapaConverter.convierteDeEntidad(etapa));
		}
		
		TreeMap<String, String> menuBread = ConstructorBreadcrumb.construyeLiga(temporada.get().getLigaHistorico());
		
		model.addAttribute("menuBread", menuBread);
		model.addAttribute("menuActivo", resultado.get().getNombre());
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
