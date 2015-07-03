package deportes.beisbol.jpa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import deportes.beisbol.jpa.model.Equipo;

public interface EquipoRepository extends JpaRepository<Equipo, Short>,
    QueryDslPredicateExecutor<Equipo> {
	
	/* @Query("SELECT e FROM Equipo e WHERE e.participante.temporada.id = :id AND e.campeon = 1")
	public Equipo findCampeon(@Param("id") short id); */
	
	/* @Query("SELECT e FROM Equipo e WHERE e.abreviatura = :siglasEquipo "
			+ "AND e.franquiciaHistorico.franquicia.liga.siglasEs = :siglasLiga "
			+ "AND e.participante.temporada.id = :temporadaId")
	public Equipo findAbreviaturaLiga(@Param("siglasEquipo") String siglasEquipo, @Param("siglasLiga") String siglasLiga,
			@Param("temporadaId") short temporadaId); */
	
	/* @Query("SELECT e FROM Equipo e WHERE e.franquiciaHistorico.franquicia.id = :id")
	public Collection<Equipo> findFranquiciaId(@Param("id") Short id); */
}
