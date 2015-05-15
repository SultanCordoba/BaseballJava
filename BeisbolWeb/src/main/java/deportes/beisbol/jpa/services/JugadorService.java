package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Optional;

import deportes.beisbol.jpa.model.Jugador;
import deportes.beisbol.model.JugadorBeisbol;

public interface JugadorService {
	public Collection<JugadorBeisbol> findLikeNombre(String nombre);
	
	public Optional<Jugador> findOne(short id);
}
