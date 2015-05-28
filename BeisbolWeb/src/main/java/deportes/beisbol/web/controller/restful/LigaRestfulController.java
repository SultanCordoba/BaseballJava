package deportes.beisbol.web.controller.restful;

import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import deportes.beisbol.jpa.services.LigaService;
import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.web.model.LigaModel;

@RestController
@RequestMapping("/liga-restful")
public class LigaRestfulController {
	
	@Autowired
	LigaService ligaService;
	
	@RequestMapping(value = "/showall", method = RequestMethod.GET)
	@ResponseBody
	public Collection<LigaBeisbol> getAllLigas(Locale locale) {
		return ligaService.getAllLigas(Optional.of(locale.getLanguage()));
	}
	
	@RequestMapping(value = "/{id}/show", method = RequestMethod.GET)
	@ResponseBody 
	public LigaModel getLiga(@PathVariable Byte id, Locale locale) {
		Optional<String> idioma = Optional.of(locale.getLanguage());
		
		return ligaService.creaLigaModel(id, idioma);
	}
	
/*	@ExceptionHandler(LigaNotFoundException.class)
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
	
	@RequestMapping(value = "/pruebaPost/XXXX", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> pruebaPost(@RequestBody LigaBeisbol liga) {
		//LigaBeisbol resultado = new LigaBeisbol((byte) 0);
		
		//return resultado;
		
		/* HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue"); 
		return new ResponseEntity<String>(liga.getNombre().toUpperCase(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	} */
}
