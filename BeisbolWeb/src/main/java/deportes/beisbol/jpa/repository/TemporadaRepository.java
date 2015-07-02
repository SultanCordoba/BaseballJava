package deportes.beisbol.jpa.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import deportes.beisbol.jpa.model.LigaHistorico;
import deportes.beisbol.jpa.model.Temporada;

public interface TemporadaRepository extends JpaRepository<Temporada, Short>,
     QueryDslPredicateExecutor<Temporada> {
	// public Collection<Temporada> findByLigaHistorico(LigaHistorico liga);
	
	// public Collection<Temporada> findByLigaHistoricoOrderByFechaInicioDesc(LigaHistorico liga);
	
	//public Temporada findByNombreAndLigaHistorico(String nombre, LigaHistorico liga);
	
	/*@Query("SELECT t FROM Temporada t WHERE :fecha BETWEEN t.fechaInicio AND t.fechaFin")
	public Collection<Temporada> buscarActuales(@Param("fecha") Date fecha);*/
}
