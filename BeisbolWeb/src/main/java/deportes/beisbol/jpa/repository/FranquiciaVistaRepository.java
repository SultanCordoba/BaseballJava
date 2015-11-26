package deportes.beisbol.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import deportes.beisbol.jpa.model.FranquiciaVista;

public interface FranquiciaVistaRepository extends JpaRepository<FranquiciaVista, Short>,
   QueryDslPredicateExecutor<FranquiciaVista> {

}
