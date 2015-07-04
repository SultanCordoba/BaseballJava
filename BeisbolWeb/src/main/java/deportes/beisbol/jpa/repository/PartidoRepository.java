package deportes.beisbol.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import deportes.beisbol.jpa.model.Partido;

public interface PartidoRepository extends JpaRepository<Partido, Short>,
QueryDslPredicateExecutor<Partido> {
}
