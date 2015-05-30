package deportes.beisbol.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FechaUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(FechaUtils.class);
	
	public static LocalDate convertidor(Date fecha) {
		
		if (fecha != null) {		
			try {
				return Instant.ofEpochMilli(fecha.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				// return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			}
			catch (UnsupportedOperationException uoe) {
				logger.info("Fecha fue:" + fecha);
				return null;
			}
		}
		else {
			return null;
		}
	}
}
