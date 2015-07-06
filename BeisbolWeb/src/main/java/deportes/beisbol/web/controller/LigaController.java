package deportes.beisbol.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

/* import org.slf4j.Logger;
import org.slf4j.LoggerFactory; */ 
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

import com.google.common.base.Strings;

import deportes.beisbol.ErrorInfo;
import deportes.beisbol.jpa.services.LigaService;
import deportes.beisbol.utils.ConstructorBreadcrumb;
import deportes.beisbol.web.exception.LigaNotFoundException;
import deportes.beisbol.web.model.LigaModel;

@Controller
@RequestMapping("/liga")
public class LigaController {
	
	//private static final Logger logger = LoggerFactory.getLogger(LigaController.class);
	
	@Autowired
	LigaService ligaService;
	
	@RequestMapping(value = "/showall", method = RequestMethod.GET)
	public String showAllLigas(Model model, Locale locale) {
		
		// HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
		model.addAttribute("menuBread", ConstructorBreadcrumb.construyeInicio());
		model.addAttribute("menuActivo", "Xheader.ligas");
		model.addAttribute("ligas", ligaService.getAllLigas(Strings.nullToEmpty(locale.getLanguage())));
				
		return "../templates/liga/showall";
	}
	
	@RequestMapping(value = "/{id}/show/{zona}", method = RequestMethod.GET)
	public String showLiga(@PathVariable Byte id, @PathVariable String zona, Model model, 
			Locale locale) {

		LigaModel ligaModelo = ligaService.creaLigaModel(id, Strings.nullToEmpty(locale.getLanguage()));
		
		model.addAttribute("menuBread", ConstructorBreadcrumb.construyeLigasAll());
		model.addAttribute("menuActivo", ligaModelo.getLiga().getNombre());		
		model.addAttribute("modelo", ligaModelo);
		model.addAttribute("zona", "#"+zona);
		model.addAttribute("zonaTab", "#zT"+zona);

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
}
