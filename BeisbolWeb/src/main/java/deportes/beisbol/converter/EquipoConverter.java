package deportes.beisbol.converter;

import deportes.beisbol.jpa.model.Equipo;
import deportes.beisbol.model.EquipoBeisbol;

public class EquipoConverter {
	public static EquipoBeisbol convierteDeBase(Equipo equipoBase) {
		EquipoBeisbol resultado = new EquipoBeisbol();
		
		try {
			resultado.setNombre(equipoBase.getNombreCompletoEs());
			resultado.setNombreTabla(equipoBase.getNombreTablasEs());
			resultado.setSiglas(equipoBase.getAbreviatura());
			resultado.setId(equipoBase.getId());
			resultado.setLogotipo(equipoBase.getArchivoEscudo());
		} catch (NullPointerException npe) {
			resultado.setNombre("");
			resultado.setNombreTabla("");
			resultado.setSiglas("");
			resultado.setId((short) 0);
		}
		
		/* if (idioma.isPresent()) {		
			switch (Strings.nullToEmpty(idioma.get()).toUpperCase()) {
			case "EN":
				resultado.setNombre(equipoBase.get);
				break;
			}
		} */
		
		return resultado;
	}
}
