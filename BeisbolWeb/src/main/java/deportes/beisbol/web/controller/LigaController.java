package deportes.beisbol.web.controller;

import java.util.HashSet;
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
import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.RangoFechaBeisbol;
import deportes.beisbol.service.LigaService;
import deportes.beisbol.utils.EquipoAux;
import deportes.beisbol.web.exception.LigaNotFoundException;
import deportes.core.interfaces.FranquiciaInterfaz;

@Controller
@RequestMapping("/liga")
public class LigaController {
	
	private static final Logger logger = LoggerFactory.getLogger(LigaController.class);
	
	@Autowired
	LigaService ligaService;
	
	@RequestMapping(value = "/showall", method = RequestMethod.GET)
	public String showAllLigas(Model model, Locale locale) {
		
		//RequestContext requestContext = new RequestContext(request);
		
		//logger.info("Locale de Request es " + requestContext.getLocale().getLanguage());
		
		Optional<String> idioma = Optional.of(locale.getLanguage());
		
		model.addAttribute("ligas", ligaService.findActivas(idioma));
		
		//logger.info("Locale de metodo es " + locale.getLanguage());
		
		return "../templates/liga/showall";
	}
	
	@RequestMapping(value = "/show/{id}/{zona}", method = RequestMethod.GET)
	public String showLiga(@PathVariable Byte id, @PathVariable String zona, Model model, Locale locale) {
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
				// equipos.add(equipoAux);
				equipos.put(equipoAux.getNombre(), equipoAux);
			}
			
		}
		
		/* model.addAttribute("zona", "#"+zona);
		model.addAttribute("navZona", "#link" + zona.substring(0, 1).toUpperCase() + zona.substring(1)); */
		model.addAttribute("ligaD", resultado.get());
		model.addAttribute("equipos", equipos.values());
		
		return "../templates/liga/show";
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
