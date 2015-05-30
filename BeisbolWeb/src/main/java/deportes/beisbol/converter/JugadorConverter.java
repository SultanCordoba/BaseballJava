package deportes.beisbol.converter;

import deportes.beisbol.jpa.model.Jugador;
import deportes.beisbol.model.JugadorBeisbol;
import deportes.beisbol.utils.FechaUtils;

public class JugadorConverter {
	public static JugadorBeisbol convierteDeBase(Jugador jugadorBase) {
		JugadorBeisbol resultado = new JugadorBeisbol();
		
		resultado.setId(jugadorBase.getId());
		resultado.setNombre(jugadorBase.getNombres());
		resultado.setApellidoPaterno(jugadorBase.getApellidoPaterno());
		resultado.setApellidoMaterno(jugadorBase.getApellidoMaterno());
		resultado.setFechaNacimiento(FechaUtils.convertidor(jugadorBase.getFechaNacimiento()));
		resultado.setCiudadNacimiento(jugadorBase.getCiudad().nombreCiudad());
		
		return resultado;
	}

}
