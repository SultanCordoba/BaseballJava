package deportes.beisbol.web.controller.restful;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import deportes.beisbol.jpa.services.FranquiciaService;
import deportes.beisbol.web.model.FranquiciaModel;

@RestController
@RequestMapping("/franquicia-restful")
public class FranquiciaRestfulController {

	@Autowired
	FranquiciaService franquiciaService;
	
	@RequestMapping(value = "{id}/show", method = RequestMethod.GET)
	@ResponseBody
	public FranquiciaModel showFranquicia(@PathVariable Short id, Locale locale) {
		return franquiciaService.creaFranquiciaModelo(id, Optional.of(locale.getLanguage()));
	}
	
}
