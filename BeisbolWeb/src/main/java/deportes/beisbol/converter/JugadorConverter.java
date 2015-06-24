package deportes.beisbol.converter;

import deportes.beisbol.jpa.model.Jugador;
import deportes.beisbol.model.JugadorBeisbol;
import deportes.beisbol.utils.FormatoUtils;

public class JugadorConverter {
	public static JugadorBeisbol convierteDeBase(Jugador jugadorBase) {
		JugadorBeisbol resultado = new JugadorBeisbol();
		
		resultado.setId(jugadorBase.getId());
		resultado.setNombre(jugadorBase.getNombres());
		resultado.setApellidoPaterno(jugadorBase.getApellidoPaterno());
		resultado.setApellidoMaterno(jugadorBase.getApellidoMaterno());
		resultado.setFechaNacimiento(FormatoUtils.convertidorFecha(jugadorBase.getFechaNacimiento()));
		resultado.setCiudadNacimiento(FormatoUtils.nombreCiudad(jugadorBase.getCiudad()));
		resultado.setBatea(jugadorBase.getBatea());
		resultado.setTira(jugadorBase.getTira());
		return resultado;
	}

}
