package deportes.beisbol.jpa.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import deportes.beisbol.jpa.model.Roster;

public interface RosterRepository extends JpaRepository<Roster, Short> {
	public Collection<Roster> findByEquipoIdOrderByFechaInicioAsc(Short equipoId);
	
	@Query("SELECT r FROM Roster r  "
			+ "WHERE r.jugadorId = :jugadorId "
			+ "AND :fechaInicio <= r.fechaFin AND :fechaFin >= r.fechaInicio "
			+ "ORDER BY r.fechaInicio")
	public Collection<Roster> hallarRosterByJugadorAndTemproada(@Param("jugadorId") Short jugadorId,
			@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
}
