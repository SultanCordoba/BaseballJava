package deportes.beisbol.jpa.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import deportes.beisbol.jpa.model.LigaHistorico;

public interface LigaHistoricoRepository extends CrudRepository<LigaHistorico, Short>,
       QueryDslPredicateExecutor<LigaHistorico> {
}
