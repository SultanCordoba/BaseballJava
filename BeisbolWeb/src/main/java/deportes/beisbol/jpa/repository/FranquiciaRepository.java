package deportes.beisbol.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import deportes.beisbol.jpa.model.Franquicia;

public interface FranquiciaRepository extends JpaRepository<Franquicia, Short>,
    QueryDslPredicateExecutor<Franquicia>{
}
