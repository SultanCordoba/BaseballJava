package deportes.beisbol.jpa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import deportes.beisbol.jpa.model.Partido;

public interface PartidoRepository extends JpaRepository<Partido, Short> {
	
	@Query("SELECT p FROM Partido p "
			+ "WHERE p.etapa.temporada.id = :temporadaId "
			+ "ORDER BY p.etapa.id, p.fechaRealizacion")
	public Collection<Partido> findByTemporadaId(@Param("temporadaId") Short temporadaId);
}
