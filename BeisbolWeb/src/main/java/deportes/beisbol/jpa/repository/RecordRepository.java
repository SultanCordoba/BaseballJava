package deportes.beisbol.jpa.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import deportes.beisbol.jpa.model.Etapa;
import deportes.beisbol.jpa.model.Record;
import deportes.beisbol.jpa.model.Vuelta;

public interface RecordRepository extends JpaRepository<Record, Short>,
   QueryDslPredicateExecutor<Record> {

	@Query("SELECT r FROM Record r JOIN r.participante.equipos eq " 
			+ "WHERE eq.id = :equipoId AND r.etapa = :etapa AND r.vuelta = :vuelta")
	public Optional<Record> findByEquipo(@Param("equipoId") short equipoId, @Param("etapa") Etapa etapa, @Param("vuelta") Vuelta vuelta);
	
	@Query("SELECT r FROM Record r JOIN r.participante.equipos eq "
			+ "WHERE eq.franquiciaHistorico.franquicia.id = :franquiciaId " 
			+ "AND r.etapa.temporada.id = :temporadaId "
			+ "ORDER BY r.etapa.ordenRonda DESC")
	public Collection<Record> findByParticipanteTemporada(@Param("franquiciaId") Short franquiciaId, @Param("temporadaId") Short temporadaId);
}
