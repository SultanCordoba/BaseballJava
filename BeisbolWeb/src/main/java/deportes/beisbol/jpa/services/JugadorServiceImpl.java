package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deportes.beisbol.converter.JugadorConverter;
import deportes.beisbol.jpa.model.Jugador;
import deportes.beisbol.jpa.repository.JugadorRepository;
import deportes.beisbol.model.JugadorBeisbol;
import static deportes.beisbol.jpa.predicates.JugadorPredicates.nombreIsLike;

@Service
@Transactional(readOnly = true)
public class JugadorServiceImpl implements JugadorService {

	private static final Logger logger = LoggerFactory.getLogger(JugadorServiceImpl.class);
	
	@Autowired
	JugadorRepository jugadorRepository;
	
	@Override
	public Collection<JugadorBeisbol> findLikeNombre(String nombre) {
		Iterator<Jugador> iteraJugadores = jugadorRepository.findAll(nombreIsLike(nombre)).iterator();
		LinkedHashSet<JugadorBeisbol> resultado = new LinkedHashSet<>();
		Jugador pasoJugador;
				
		while (iteraJugadores.hasNext()) {
			
			pasoJugador = iteraJugadores.next();
			
			logger.info("Nombre:" + pasoJugador.getNombres());
			logger.info("Apellido Paterno:" + pasoJugador.getApellidoPaterno());
			logger.info("Apellido Materno:" + pasoJugador.getApellidoMaterno());
			
			resultado.add(JugadorConverter.convierteDeBase(pasoJugador));
		}
		
		return resultado;
	}

	public Iterable<Jugador> findAll() {
		return jugadorRepository.findAll();
	}

	/* public Jugador save(Jugador j) {		
		
		return jugadorRepository.save(j);
	} */

	@Override
	public Optional<Jugador> findOne(short id) {
		return Optional.ofNullable(jugadorRepository.findOne(id));
	}
}
