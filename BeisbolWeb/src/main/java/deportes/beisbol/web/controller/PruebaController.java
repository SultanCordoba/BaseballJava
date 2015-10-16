package deportes.beisbol.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Joiner;

@RestController
@RequestMapping("/prueba")
public class PruebaController {
	
	private static final Logger logger = LoggerFactory.getLogger(PruebaController.class);
	
	@RequestMapping(value = "/paso", method = RequestMethod.POST,
			headers = "Accept=application/json")
	@ResponseBody 
	public ResponseEntity<String> guardar(@RequestBody List<HashMap<String,String>> transacciones) {
		
		String resultado;
		
		Joiner joiner = Joiner.on("-");
		ArrayList<String> temporalString = new ArrayList<>();
		
		Iterator<HashMap<String, String>> iteraTransacciones = transacciones.iterator();
		
		while (iteraTransacciones.hasNext()) {
			HashMap<String, String> paso2 = iteraTransacciones.next();
			logger.info(paso2.get("CARGO"));
			temporalString.add(paso2.get("CARGO"));
		}
		
		resultado = joiner.join(temporalString).toString();
		
		return new ResponseEntity<String>(resultado, new HttpHeaders(), HttpStatus.OK);
	}
}
