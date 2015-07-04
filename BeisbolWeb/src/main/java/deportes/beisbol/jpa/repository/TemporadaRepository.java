package deportes.beisbol.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import deportes.beisbol.jpa.model.Temporada;

public interface TemporadaRepository extends JpaRepository<Temporada, Short>,
     QueryDslPredicateExecutor<Temporada> {
}
