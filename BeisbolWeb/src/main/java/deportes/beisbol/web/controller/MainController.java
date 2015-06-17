package deportes.beisbol.web.controller;

import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import deportes.beisbol.jpa.services.TemporadaService;
import deportes.beisbol.web.model.InicioModel;
import deportes.beisbol.web.model.TemporadaActual;

@Controller
// @RequestMapping("/inicio")
public class MainController {
	
	@Autowired
	TemporadaService temporadaService;
	
	@RequestMapping(value = "/inicio")
	public String inicio(Model model) {
		
		InicioModel inicioModelo = new InicioModel();
		inicioModelo.setTemporadasEnCurso((LinkedHashSet<TemporadaActual>) temporadaService.buscarActuales());
		
		model.addAttribute("menuActivo", "Xheader.inicio");
		model.addAttribute("modelo", inicioModelo);
				
		return "../templates/inicio/inicio";
	}


}
