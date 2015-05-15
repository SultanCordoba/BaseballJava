package deportes.beisbol.jpa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import deportes.beisbol.jpa.model.Roster;

public interface RosterRepository extends JpaRepository<Roster, Short> {
	public Collection<Roster> findByEquipoIdOrderByFechaInicioAsc(Short equipoId);
}
