package deportes.beisbol;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;

import deportes.beisbol.lectores.LectorMultiPartidos;
import deportes.beisbol.lectores.LectorPartidoSencillo;
import deportes.beisbol.web.util.EnumReaderActions;

public class AppUnicaFecha {
	public static void main(String[] args) {
		
		final Logger logger = LoggerFactory.getLogger(App.class);
				
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
				
		boolean bProcesaSencillo;
		int j;

		// Se obtiene la fecha de hoy
		Date input = new Date();
		
		// Pero se buscan los partidos del día anterior. Por eso al final el minusDays.
		LocalDate date = input.toInstant().atZone
				(ZoneId.systemDefault()).toLocalDate().minusDays(1);
		
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
		
		logger.info("FIN DE PROCEDIMIENTO");
    }
}
