package deportes.beisbol.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class FechaUtils {
	
	public static LocalDate convertidor(Date fecha) {
		return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
