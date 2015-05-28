package deportes.beisbol.web.controller.restful;

import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import deportes.beisbol.ErrorInfo;
import deportes.beisbol.component.LigaComponent;
import deportes.beisbol.jpa.services.LigaService;
import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.web.exception.LigaNotFoundException;
import deportes.beisbol.web.model.LigaModel;

@RestController
@RequestMapping("/liga-restful")
public class LigaRestfulController {
	
	@Autowired
	LigaService ligaService;
	
	@Autowired
	LigaComponent ligaComponent;
	
	@RequestMapping(value = "/showall", method = RequestMethod.GET)
	@ResponseBody
	public Collection<LigaBeisbol> getAllLigas(Locale locale) {
		return ligaComponent.getAllLigas(Optional.of(locale.getLanguage()));
	}
	
	@RequestMapping(value = "/{id}/show", method = RequestMethod.GET)
	@ResponseBody 
	public LigaModel getLiga(@PathVariable Byte id, Locale locale) {
		Optional<LigaBeisbol> resultado;
		Optional<String> idioma = Optional.of(locale.getLanguage());
		
		resultado = ligaService.findOne(id, idioma);
		
		resultado.orElseThrow(() -> new LigaNotFoundException(id));
		
		// return resultado.get();
		
		return ligaComponent.creaLigaModel(id, idioma);
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
	
	@RequestMapping(value = "/pruebaPost/XXXX", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> pruebaPost(@RequestBody LigaBeisbol liga) {
		//LigaBeisbol resultado = new LigaBeisbol((byte) 0);
		
		//return resultado;
		
		/* HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue"); */
		return new ResponseEntity<String>(liga.getNombre().toUpperCase(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
