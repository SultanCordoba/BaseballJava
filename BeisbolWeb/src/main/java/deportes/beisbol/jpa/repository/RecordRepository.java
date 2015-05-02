package deportes.beisbol.jpa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import deportes.beisbol.jpa.model.Franquicia;
import deportes.beisbol.jpa.model.Record;

public interface RecordRepository extends JpaRepository<Record, Short> {
	
	@Query("SELECT r FROM Record r JOIN r.participante.equipos eq "
			+ "WHERE eq.franquiciaHistorico.franquicia.id = :franquiciaId "
			+ "ORDER BY r.etapa.temporada.fechaInicio, r.etapa.id DESC")
	public Collection<Record> findByParticipanteOrdenados(@Param("franquiciaId") Short franquiciaId);
}
