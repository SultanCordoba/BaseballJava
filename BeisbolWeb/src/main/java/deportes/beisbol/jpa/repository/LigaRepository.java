package deportes.beisbol.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import deportes.beisbol.jpa.model.Liga;

public interface LigaRepository extends JpaRepository<Liga, Byte>,  
QueryDslPredicateExecutor<Liga> {
	
}
