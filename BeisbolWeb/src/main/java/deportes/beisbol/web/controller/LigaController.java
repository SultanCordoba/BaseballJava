package deportes.beisbol.web.controller;

import java.util.Iterator;
import java.util.Locale;
import java.util.Optional;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import deportes.beisbol.jpa.services.LigaService;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.model.RangoFechaBeisbol;
import deportes.beisbol.utils.ConstructorBreadcrumb;
import deportes.beisbol.utils.EquipoAux;
import deportes.beisbol.web.exception.LigaNotFoundException;

@Controller
@RequestMapping("/liga")
public class LigaController {
	
	private static final Logger logger = LoggerFactory.getLogger(LigaController.class);
	
	@Autowired
	LigaService ligaService;
	
	@RequestMapping(value = "/showall", method = RequestMethod.GET)
	public String showAllLigas(Model model, Locale locale) {
		
		Optional<String> idioma = Optional.of(locale.getLanguage());
		
		// HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		TreeMap<String, String> menuBread = ConstructorBreadcrumb.construyeInicio();
		// menuBread.remove(menuBread.lastKey());
		
		model.addAttribute("menuBread", menuBread);
		model.addAttribute("menuActivo", "Xheader.ligas");
		model.addAttribute("ligas", ligaService.findActivas(idioma));
				
		return "../templates/liga/showall";
	}
	
	@RequestMapping(value = "/{id}/show/{zona}", method = RequestMethod.GET)
	public String showLiga(@PathVariable Byte id, @PathVariable String zona, Model model, 
			Locale locale) {
		Optional<LigaBeisbol> resultado;
		
		Optional<String> idioma = Optional.of(locale.getLanguage());
		
		resultado = ligaService.findOne(id, idioma);
		
		resultado.orElseThrow(() -> new LigaNotFoundException(id));
		
		Iterator<FranquiciaBeisbol> iteradorFranquicias = (Iterator<FranquiciaBeisbol>) 
				resultado.get().getFranquicias().iterator();
		FranquiciaBeisbol franquicia = null;
		//HashSet<EquipoAux> equipos = new HashSet<>(); 
		
		TreeMap<String, EquipoAux> equipos = new TreeMap<>();
		
		EquipoAux equipoAux = null;
		
		while (iteradorFranquicias.hasNext()) {
			franquicia = iteradorFranquicias.next();
			Iterator<RangoFechaBeisbol> nombres = (Iterator<RangoFechaBeisbol>) 
					franquicia.getNombres().iterator();
			
			while (nombres.hasNext()) {
				equipoAux = new EquipoAux();
				equipoAux.setIdFranquicia(franquicia.getId());
				equipoAux.setNombre(nombres.next().getNombre());
				equipoAux.setPais(franquicia.getPais());
				equipos.put(equipoAux.getNombre(), equipoAux);
			}
			
		}
			
		// HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		TreeMap<String, String> menuBread = ConstructorBreadcrumb.construyeLigasAll();
		
		model.addAttribute("menuBread", menuBread);
		model.addAttribute("menuActivo", resultado.get().getNombre());
		model.addAttribute("zona", "#"+zona);
		model.addAttribute("zonaTab", "#zT"+zona);
		/* model.addAttribute("navZona", "#link" + zona.substring(0, 1).toUpperCase() + zona.substring(1)); */
		model.addAttribute("ligaD", resultado.get());
		model.addAttribute("equipos", equipos.values());
		
		return "../templates/liga/show";
	}
	
	@RequestMapping(value = "/{idLiga}/franquicia/{idFranq}/temporada/{idTemp}", method = RequestMethod.GET)
	public String showFranquiciaTemporada(@PathVariable Byte idLiga, @PathVariable Short idFranq, 
			@PathVariable Short idTemp, Model model, Locale locale)
	{
		return "../templates/equipo/show";
	}
	
	@ExceptionHandler(LigaNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorInfo handleLigaNotFoundException(HttpServletRequest req, 
			LigaNotFoundException ex) {
		// Locale locale = LocaleContextHolder.getLocale();
		// String errorMessage = messageSource.getMessage("error.bad.smartphone.id", null, locale);
		String errorMessage = ex.getMessage();

		//errorMessage += ex.getValue();
		String errorURL = req.getRequestURL().toString();

		return new ErrorInfo(errorURL, errorMessage);
	}
	
	/* @RequestMapping(value = "/pruebaPost/XXXX", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> pruebaPost(@RequestBody LigaBeisbol liga) {
		//LigaBeisbol resultado = new LigaBeisbol((byte) 0);
		
		//return resultado;
		
		/* HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue"); */
		/*return new ResponseEntity<String>(liga.getNombre().toUpperCase(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	} */
}
