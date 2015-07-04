package deportes.beisbol.web.controller.restful;

import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import deportes.beisbol.ErrorInfo;
import deportes.beisbol.jpa.services.TemporadaService;
import deportes.beisbol.web.model.TemporadaModel;

@RestController
@RequestMapping("/temporada-restful")
public class TemporadaRestfulController {
	
	@Autowired
	TemporadaService temporadaService;
	
	@RequestMapping(value = "/{id}/show", method = RequestMethod.GET)
	@ResponseBody 
	public TemporadaModel getTemporada(@PathVariable Short id, Locale locale) {
		return temporadaService.crearTemporadaModel(id, Optional.of(locale.getLanguage()));
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

class TemporadaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6779250917217620051L;

	public TemporadaNotFoundException(String siglas, String nombre) {
		super("No hay temporada '" + nombre + "' en la liga " + siglas + ".");
	}
}
