package deportes.beisbol.jpa.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import deportes.beisbol.jpa.model.Jugador;

public interface JugadorRepository extends CrudRepository<Jugador, Short>,
   QueryDslPredicateExecutor<Jugador> {

}
