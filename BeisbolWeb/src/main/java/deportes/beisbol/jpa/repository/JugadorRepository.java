package deportes.beisbol.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import deportes.beisbol.jpa.model.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Short>,
   QueryDslPredicateExecutor<Jugador> {

}
