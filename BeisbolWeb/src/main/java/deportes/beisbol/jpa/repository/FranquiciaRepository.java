package deportes.beisbol.jpa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import deportes.beisbol.jpa.model.Franquicia;

public interface FranquiciaRepository extends JpaRepository<Franquicia, Short> {
	
	@Query("SELECT f FROM Franquicia f WHERE f.liga.id = :id ORDER BY f.nombreTablasEs")
	public Collection<Franquicia> findLigaId(@Param("id") Byte id);
}
