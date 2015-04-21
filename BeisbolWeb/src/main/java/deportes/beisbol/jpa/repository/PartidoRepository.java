package deportes.beisbol.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import deportes.beisbol.jpa.model.Partido;

public interface PartidoRepository extends JpaRepository<Partido, Short> {
		
}
