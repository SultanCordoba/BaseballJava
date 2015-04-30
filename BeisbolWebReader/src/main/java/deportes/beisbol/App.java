package deportes.beisbol;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;

import deportes.beisbol.lectores.LectorMultiPartidos;
import deportes.beisbol.lectores.LectorPartidoSencillo;
import deportes.beisbol.web.util.EnumReaderActions;

public class App {
	public static void main(String[] args) {
			
		final Logger logger = LoggerFactory.getLogger(App.class);
				
		// System.out.println(rutaPropiedades);
		
		/* try {
			propiedadesLector.load(new FileInputStream(rutaPropiedades));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} */
		
		logger.info("INICIO DE PROCEDIMIENTO");
		
		LectorMultiPartidos lectorMultiPartidos = null;
		LectorPartidoSencillo lectorPartidoSencillo = null;
		
		Properties propiedadesLector = new Properties();
		
		String rutaPropiedades = System.getProperty("user.dir") + 
				File.separator + "lector.properties";
		
		try {
			propiedadesLector.load(new FileInputStream(rutaPropiedades));
			lectorMultiPartidos = new LectorMultiPartidos();
			lectorPartidoSencillo = new LectorPartidoSencillo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
				
		/* lectorMultiPartidos.setGrupoLiga(propiedadesLector.getProperty("grupoLiga"));
		lectorMultiPartidos.setLiga(propiedadesLector.getProperty("liga"));

		lectorPartidoSencillo.setGrupoLiga(propiedadesLector.getProperty("grupoLiga"));
		lectorPartidoSencillo.setLiga(propiedadesLector.getProperty("liga")); */
		
		LocalDate startDate = LocalDate.parse(propiedadesLector.getProperty("fechaInicio"), 
				DateTimeFormatter.ofPattern(lectorMultiPartidos.formatoFecha));
		LocalDate endDate = LocalDate.parse(propiedadesLector.getProperty("fechaFin"), 
				DateTimeFormatter.ofPattern(lectorMultiPartidos.formatoFecha));
		
		/*lectorPartidos.setGrupoLiga("aaa");  // valores aaa o win
		lectorPartidos.setLiga("MEX");   // valores MEX o LMP, SC
		
		LocalDate startDate = LocalDate.parse("2014-04-28", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate endDate = LocalDate.parse("2014-05-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")); */

		boolean bProcesaSencillo;
		int j;
		
		// Se agrega una suma de un día en la condición de salida para emular un "menor o igual".
		for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1))
		{
			bProcesaSencillo = false;
			j = 0;
			try {
				lectorMultiPartidos.setFechaJuegos(date);
				j = lectorMultiPartidos.obtieneJuegos(EnumReaderActions.GUARDAR_PARTIDOS); 
		    } catch (HttpMessageNotReadableException hmnre) {
		    	bProcesaSencillo = true;
		    }
			
			if (bProcesaSencillo) {
				try {
					lectorPartidoSencillo.setFechaJuegos(date);
					j = lectorPartidoSencillo.obtieneJuegos(EnumReaderActions.GUARDAR_PARTIDOS);
					j = 1;
				} catch (HttpMessageNotReadableException hmnre) {
					logger.info("Estructura no contemplada para fecha " + date);
				}
			}
			
		    logger.info("Se encontraron " + j + " juegos para " + date);
		} 
		
		logger.info("FIN DE PROCEDIMIENTO");
		
		/* RestTemplate restTemplate = new RestTemplate();
		
		//restTemplate.setErrorHandler(new MyResponseErrorHandler());
		
		String url = "http://localhost:8090/baseball/temporada/LMB/get/1972";
		ResponseEntity<TemporadaBeisbol> regreso = restTemplate.getForEntity(url, TemporadaBeisbol.class);

		
		// String url = "http://localhost:8090/baseball/liga/pruebaPost/XXXX";
		/* LigaBeisbol origen = restTemplate.getForObject(url, LigaBeisbol.class);
		System.out.println(origen.getNombre()); */
		
		/* LigaBeisbol origen = new LigaBeisbol((byte) 0);
		origen.setNombre("Mamadas");
		ResponseEntity<String> regreso = 
				restTemplate.postForEntity(url, origen, String.class); */
		
		/* if (regreso.getStatusCode() != HttpStatus.OK) {
			System.out.println("Estatus error es " + regreso.getStatusCode().toString());
		}
		else {
			System.out.println(regreso.getBody());
		} */

    }
}
