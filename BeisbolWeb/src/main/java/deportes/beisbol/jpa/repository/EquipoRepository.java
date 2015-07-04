package deportes.beisbol.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import deportes.beisbol.jpa.model.Equipo;

public interface EquipoRepository extends JpaRepository<Equipo, Short>,
    QueryDslPredicateExecutor<Equipo> {
}
