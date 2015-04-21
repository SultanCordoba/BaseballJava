package deportes.beisbol.jpa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import deportes.beisbol.jpa.model.Etapa;
import deportes.beisbol.jpa.model.Temporada;

public interface EtapaRepository extends JpaRepository<Etapa, Short> {
	public Etapa findByNombreAndTemporada(String nombre, Temporada temporada);
	
	public Collection<Etapa> findByTemporada(Temporada temporada);	
}
