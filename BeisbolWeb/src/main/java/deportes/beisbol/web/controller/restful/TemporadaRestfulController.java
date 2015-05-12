package deportes.beisbol.web.controller.restful;

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
import deportes.beisbol.model.EquipoBeisbol;
import deportes.beisbol.model.TemporadaBeisbol;
import deportes.beisbol.service.EquipoService;
import deportes.beisbol.service.EtapaService;
import deportes.beisbol.jpa.services.LigaService;
import deportes.beisbol.jpa.services.TemporadaService;

@RestController
@RequestMapping("/temporada-restful")
public class TemporadaRestfulController {
	
	@Autowired
	LigaService ligaService;
	
	@Autowired
	TemporadaService temporadaService;
	
	@Autowired
	EquipoService equipoService;
	
	@Autowired
	EtapaService etapaService;
	
	@RequestMapping(value = "/{siglas}/get/{nombre}", method = RequestMethod.GET)
	@ResponseBody 
	public TemporadaBeisbol getTemporada(@PathVariable String siglas,
			@PathVariable String nombre) {
		Optional<TemporadaBeisbol> resultado;
		
		resultado = temporadaService.findByNombreAndLiga(nombre, siglas);
		
		resultado.orElseThrow(() -> new TemporadaNotFoundException(siglas, nombre));
		
		if (resultado.isPresent()) {
			
			resultado.get().setEtapas(etapaService.findEtapasByTemporada(resultado.get(), Optional.ofNullable("ES")));
			
			Optional<EquipoBeisbol> campeon = equipoService.findCampeon(resultado.get());
			
			if (campeon.isPresent()) {
				resultado.get().setCampeon(campeon.get());
			}
		}
		
		return resultado.get();
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

class TemporadaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6779250917217620051L;

	public TemporadaNotFoundException(String siglas, String nombre) {
		super("No hay temporada '" + nombre + "' en la liga " + siglas + ".");
	}
}
