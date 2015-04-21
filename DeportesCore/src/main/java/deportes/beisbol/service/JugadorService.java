package deportes.beisbol.service;

import java.util.Collection;

import deportes.beisbol.model.JugadorBeisbol;

public interface JugadorService {
	public Collection<JugadorBeisbol> findLikeNombre(String nombre);
}
