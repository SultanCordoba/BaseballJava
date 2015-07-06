package deportes.beisbol.web.controller.restful;

import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;

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
		return ligaService.getAllLigas(Strings.nullToEmpty(locale.getLanguage()));
	}
	
	@RequestMapping(value = "/{id}/show", method = RequestMethod.GET)
	@ResponseBody 
	public LigaModel getLiga(@PathVariable Byte id, Locale locale) {		
		return ligaService.creaLigaModel(id, Strings.nullToEmpty(locale.getLanguage()));
	}
}
