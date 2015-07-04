package deportes.beisbol.jpa.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import deportes.beisbol.jpa.model.LigaHistoricoInt;

public interface LigaHistoricoIntRepository extends CrudRepository<LigaHistoricoInt, Short>,
      QueryDslPredicateExecutor<LigaHistoricoInt> {
}
