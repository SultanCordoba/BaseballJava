package deportes.beisbol.converter;

import java.util.Optional;

/* import org.slf4j.Logger;
import org.slf4j.LoggerFactory; */

import com.google.common.base.Strings;

import deportes.beisbol.jpa.model.Equipo;
import deportes.beisbol.model.EquipoBeisbol;

public class EquipoConverter {
	
	//private static final Logger logger = LoggerFactory.getLogger(EquipoConverter.class);
	
	public static EquipoBeisbol convierteDeBase(Equipo equipoBase, Optional<String> idioma) {
		EquipoBeisbol resultado = new EquipoBeisbol();
		
		try {
			resultado.setNombre(equipoBase.getNombreCompletoEs());
			resultado.setNombreTabla(equipoBase.getNombreTablasEs());
			resultado.setSiglas(equipoBase.getAbreviatura());
			resultado.setId(equipoBase.getId());
			resultado.setLogotipo(equipoBase.getArchivoEscudo());
			
			String idiomaCompara = Strings.nullToEmpty(idioma.get()).toUpperCase();
			if (!idiomaCompara.equalsIgnoreCase("ES")) {						
				
				String nombreIdioma = FranquiciaConverter.nombreCompletoIdioma(equipoBase.getFranquiciaHistorico(), idioma);
				resultado.setNombre(nombreIdioma);
			} 
		} catch (NullPointerException npe) {
			resultado.setNombre("");
			resultado.setNombreTabla("");
			resultado.setSiglas("");
			resultado.setId((short) 0);
		}

		return resultado;
	}
}
