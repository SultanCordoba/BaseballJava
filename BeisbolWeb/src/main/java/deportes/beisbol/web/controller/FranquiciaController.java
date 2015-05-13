package deportes.beisbol.web.controller;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import deportes.beisbol.jpa.model.Liga;
import deportes.beisbol.jpa.model.LigaHistorico;
import deportes.beisbol.jpa.services.LigaService;
import deportes.beisbol.jpa.services.RecordService;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.LigaBeisbol;
import deportes.beisbol.jpa.services.FranquiciaService;
import deportes.beisbol.utils.ConstructorBreadcrumb;
import deportes.beisbol.utils.RecordEtapa;

@Controller
@RequestMapping("/franquicia")
public class FranquiciaController {
	
	@Autowired
	FranquiciaService franquiciaService;
	
	@Autowired
	RecordService recordService;
	
	@Autowired
	LigaService ligaService;
	
	private LinkedHashSet<RecordEtapa> condensarRecords(LinkedHashSet<RecordEtapa> temporadas) {
		LinkedHashSet<RecordEtapa> resultado = new LinkedHashSet<RecordEtapa>();
		
		String temporadaActual = "XXXX";
		String etapaActual = "XXXX";
		
		Iterator<RecordEtapa> iteraRecords = temporadas.iterator();
		RecordEtapa paso;
		RecordEtapa condensado = null;
		
		while (iteraRecords.hasNext()) {
			paso = iteraRecords.next();
			
			if (!paso.getTemporadaNombre().equalsIgnoreCase(temporadaActual)) {
				
				if (!temporadaActual.endsWith("XXXX")) {
					resultado.add(condensado);
				}
				
				temporadaActual = paso.getTemporadaNombre();
				etapaActual = paso.getEtapaNombre();
				
				condensado = new RecordEtapa();
				condensado.setTemporadaNombre(temporadaActual);
				condensado.setEtapaNombre(etapaActual);
				condensado.setGanados(paso.getGanados());
				condensado.setPerdidos(paso.getPerdidos());
				condensado.setCampeon(paso.isCampeon());
				condensado.setParticipanteId(paso.getParticipanteId());
			}
			else {
				condensado.setGanados(condensado.getGanados() + paso.getGanados());
				condensado.setPerdidos(condensado.getPerdidos() + paso.getPerdidos());
			}
		}
		
		if (!temporadaActual.endsWith("XXXX")) {
			resultado.add(condensado);
		}
		
		return resultado;
	}
	
	@RequestMapping(value = "{id}/show", method = RequestMethod.GET)
	public String showFranquicia(@PathVariable Short id, Model model, Locale locale) {
		
		Optional<FranquiciaBeisbol> resultado;		
		Optional<String> idioma = Optional.of(locale.getLanguage());
		
		resultado = franquiciaService.findById(id);
		
		model.addAttribute("franquicia", resultado.get());
		model.addAttribute("pais", resultado.get().getPais());
		
		Optional<LigaBeisbol> ligaBeisbol = franquiciaService.findLigaPorFranquicia(resultado.get().getId(), idioma);
		
		model.addAttribute("liga", ligaBeisbol.get());
		
		LinkedHashSet<RecordEtapa> temporadasEquipo = (LinkedHashSet<RecordEtapa>) 
				recordService.findTemporadasEquipos(resultado.get().getId(), idioma);
		
		model.addAttribute("temporadas", condensarRecords(temporadasEquipo));
		
		Liga ligaPaso = ligaService.findOneBd(ligaBeisbol.get().getId()).get();
		
		Iterator<LigaHistorico> ligaHistoricos = ligaPaso.getLigaHistoricos().iterator();
		
		TreeMap<String, String> menuBread = ConstructorBreadcrumb.construyeLiga(ligaHistoricos.next(), "equipos");
		
		model.addAttribute("menuBread", menuBread);
		model.addAttribute("menuActivo", resultado.get().getNombre());
		
		return "../templates/franquicia/show";
	}

}
