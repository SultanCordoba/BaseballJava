package deportes.beisbol.web.controller.restful;

import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

/* import org.slf4j.Logger;
import org.slf4j.LoggerFactory; */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import deportes.beisbol.jpa.services.PartidoService;
import deportes.beisbol.model.PartidoBeisbol;

@RestController
@RequestMapping("/partido-restful")
public class PartidoRestfulController {

	// private static final Logger logger = LoggerFactory.getLogger(PartidoRestfulController.class);
	
	@Autowired
	PartidoService partidoService;
	
	@RequestMapping(value = "/temporada/{temporadaId}/show", method = RequestMethod.GET)
	@ResponseBody
	public Collection<PartidoBeisbol> showPartidosTemporada(@PathVariable Short temporadaId, Locale locale) {
		
		return partidoService.findByTemporada(temporadaId, Optional.of(locale.getLanguage()));
	}
}
