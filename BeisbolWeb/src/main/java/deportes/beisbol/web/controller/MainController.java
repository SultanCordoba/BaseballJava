package deportes.beisbol.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inicio")
public class MainController {
	
	@RequestMapping(value = "/")
	public String inicio() {
		
		return "../templates/inicio/inicio";
	}


}
