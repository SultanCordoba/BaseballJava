package deportes.beisbol.jpa.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import deportes.beisbol.converter.JugadorConverter;
import deportes.beisbol.jpa.model.Jugador;
import deportes.beisbol.jpa.repository.JugadorRepository;
import deportes.beisbol.model.JugadorBeisbol;
import deportes.beisbol.utils.PaginaDefinidor;
import deportes.beisbol.web.model.JugadorModel;
import static deportes.beisbol.jpa.predicates.JugadorPredicates.nombreIsLike;

@Service
@Transactional(readOnly = true)
public class JugadorServiceImpl implements JugadorService {

	private static final Logger logger = LoggerFactory.getLogger(JugadorServiceImpl.class);
	
	@Autowired
	JugadorRepository jugadorRepository;
	
	@Override
	public Collection<JugadorBeisbol> search(String nombre, PaginaDefinidor pagina) {
		
		//logger.info(pagina.getInicio() + " - " + pagina.getLongitud() + " - " + pagina.getNumeroPagina());
		
		PageRequest pageRequest = new PageRequest(pagina.getNumeroPagina() - 1, 
				pagina.getLongitud(), pagina.getSort("apellidoPaterno"));
		
		Iterator<Jugador> iteraJugadores = jugadorRepository.findAll
				(nombreIsLike(nombre), pageRequest).iterator();  

		/* Iterator<Jugador> iteraJugadores = jugadorRepository.findAll
				(nombreIsLike(nombre)).iterator(); */

		
		LinkedHashSet<JugadorBeisbol> resultado = new LinkedHashSet<>();
		Jugador pasoJugador;
				
		while (iteraJugadores.hasNext()) {
			
			pasoJugador = iteraJugadores.next();
			
			/* logger.info("Nombre:" + pasoJugador.getNombres());
			logger.info("Apellido Paterno:" + pasoJugador.getApellidoPaterno());
			logger.info("Apellido Materno:" + pasoJugador.getApellidoMaterno()); */
			
			resultado.add(JugadorConverter.convierteDeBase(pasoJugador));
		}
		
		return resultado;
	}

	@Override
	public Collection<JugadorBeisbol> findAll() {
		Iterator<Jugador> iteraJugadores = jugadorRepository.findAll().iterator();
		LinkedHashSet<JugadorBeisbol> resultado = new LinkedHashSet<>();
		Jugador pasoJugador;
				
		while (iteraJugadores.hasNext()) {			
			pasoJugador = iteraJugadores.next();			
			resultado.add(JugadorConverter.convierteDeBase(pasoJugador));
		}
		
		return resultado;
	}

	/* public Jugador save(Jugador j) {		
		
		return jugadorRepository.save(j);
	} */

	@Override
	public Optional<Jugador> findOne(short id) {
		return Optional.ofNullable(jugadorRepository.findOne(id));
	}

	@Override
	public short totalRegistros() {
		return (short) Lists.newArrayList(jugadorRepository.findAll()).size();
	}

	@Override
	public short totalRegistros(String nombre) {
		return (short) Lists.newArrayList(jugadorRepository.findAll
				(nombreIsLike(nombre))).size();
	}

	@Override
	public JugadorModel getJugador(short id) {
		JugadorModel resultado = new JugadorModel();
		
		resultado.setJugador(JugadorConverter.convierteDeBase(jugadorRepository.findOne(id)));
		
		return resultado;
	}
}
