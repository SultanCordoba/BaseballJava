package deportes.beisbol.converter;

import deportes.beisbol.jpa.model.Equipo;
import deportes.beisbol.model.EquipoBeisbol;

/* import org.slf4j.Logger;
import org.slf4j.LoggerFactory; */

public class EquipoConverter {
	
	//private static final Logger logger = LoggerFactory.getLogger(EquipoConverter.class);
	
	public static EquipoBeisbol convierteDeBase(Equipo equipoBase, String idioma) {
		EquipoBeisbol resultado = new EquipoBeisbol();
		
		try {
			resultado.setNombre(equipoBase.getNombreCompletoEs());
			resultado.setNombreTabla(equipoBase.getNombreTablasEs());
			resultado.setSiglas(equipoBase.getAbreviatura());
			resultado.setId(equipoBase.getId());
			resultado.setLogotipo(equipoBase.getArchivoEscudo());
			
			String idiomaCompara = idioma.toUpperCase();
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
