package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Optional;

import deportes.beisbol.jpa.model.Jugador;
import deportes.beisbol.model.JugadorBeisbol;
import deportes.beisbol.utils.PaginaDefinidor;
import deportes.beisbol.web.model.JugadorModel;

public interface JugadorService {
	public Collection<JugadorBeisbol> search(String nombre, PaginaDefinidor pagina, Optional<String> idioma);
	
	public Optional<Jugador> findOne(short id);
	
	public Collection<JugadorBeisbol> findAll();
	
	public short totalRegistros();
	
	public short totalRegistros(String nombre, Optional<String> idioma);
	
	public JugadorModel getJugador(short id);
}
