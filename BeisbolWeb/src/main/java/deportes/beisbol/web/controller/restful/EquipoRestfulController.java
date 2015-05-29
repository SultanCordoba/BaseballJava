package deportes.beisbol.web.controller.restful;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import deportes.beisbol.jpa.services.EquipoService;
import deportes.beisbol.web.model.EquipoModel;

@RestController
@RequestMapping("/equipo-restful")
public class EquipoRestfulController {

	@Autowired
	EquipoService equipoService;
	
	@RequestMapping(value = "{id}/show", method = RequestMethod.GET)
	public EquipoModel getEquipo(@PathVariable short id, Locale locale) {
		EquipoModel equipoModelo = equipoService.creaEquipoModel(id, Optional.of(locale.getLanguage()));
		equipoModelo.setParticipante(null);
		
		return equipoModelo;
	}
}
