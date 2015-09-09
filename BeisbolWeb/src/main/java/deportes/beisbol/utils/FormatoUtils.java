package deportes.beisbol.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

import deportes.beisbol.jpa.model.Ciudad;

public class FormatoUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(FormatoUtils.class);
	
	public static LocalDate convertidorFecha(Date fecha) {
		
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
	
	public static String nombreCiudad(Ciudad ciudad) {
		final String INDEFINIDO = "INDEFINIDO";
		
		// Joiner joiner = Joiner.on(", ").skipNulls();
		ArrayList<String> datosCiudad = new ArrayList<>();
		
		if (!ciudad.getNombre().equalsIgnoreCase(INDEFINIDO)) {
			datosCiudad.add(ciudad.getNombre());
		}
		
		if (!ciudad.getEstado().getNombreEs().equalsIgnoreCase(INDEFINIDO)) {
			datosCiudad.add(ciudad.getEstado().getNombreEs());
		}
		
		if (!ciudad.getEstado().getPai().getNombreEs().equalsIgnoreCase(INDEFINIDO)) {
			datosCiudad.add(ciudad.getEstado().getPai().getAbreviaturaEs());
		}
		
		String resultado = datosCiudad.stream().filter(p -> p != null).collect(Collectors.joining(", "));
		
		//return Strings.emptyToNull(joiner.join(datosCiudad));
		return Strings.emptyToNull(resultado);
	}
}
