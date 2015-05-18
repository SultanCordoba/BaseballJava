package deportes.beisbol.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import deportes.beisbol.model.PartidoBeisbol;
import deportes.beisbol.service.PartidoService;

@RestController
@RequestMapping("/partido")
public class PartidoController {
	
	private static final Logger logger = LoggerFactory.getLogger(PartidoController.class);
	
	@Autowired
	PartidoService partidoService;
	
	@RequestMapping(value = "/guardar/{etapa}/{vuelta}", method = RequestMethod.POST,
			headers = "Accept=application/json")
	@ResponseBody 
	public ResponseEntity<String> guardar(@PathVariable short etapa, @PathVariable byte vuelta, @RequestBody PartidoBeisbol partido) {
		
		String resultado = "OP_EXITOSA"; 
		
		logger.info(partido.getClaveMilb());
		
		try {
			if (!partidoService.save(partido, etapa, vuelta)) {
				resultado = "ERROR desconocido";
			}
		} catch (Exception e) {
			resultado = "ERROR: " + e.toString() + " - " + e.getMessage();
		} 
		
		logger.info("partido: " + partido.partidoString() + " resultado:" + resultado);
		
		return new ResponseEntity<String>(resultado, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/record/{etapa}/{vuelta}", method = RequestMethod.POST, headers="Accept=application/json")
	@ResponseBody
	public ResponseEntity<String> incrementarRecord(@PathVariable short etapa, @PathVariable byte vuelta, 
			@RequestBody PartidoBeisbol partido) {
		
		String resultado = "OP_EXITOSA";
		
		
		
		return new ResponseEntity<String>(resultado, new HttpHeaders(), HttpStatus.OK);
	}
}
