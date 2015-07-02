package deportes.beisbol.jpa.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import deportes.beisbol.jpa.model.LigaHistorico;

public interface LigaHistoricoRepository extends CrudRepository<LigaHistorico, Short>,
       QueryDslPredicateExecutor<LigaHistorico> {
	
	/* @Query("SELECT lh FROM LigaHistorico lh WHERE lh.liga.id = :id")
	public Collection<LigaHistorico> buscarLigaId(@Param("id") Byte id); */
	
	// public Collection<LigaHistorico> findBySiglas(String siglas);
		
	/* @Query("SELECT lh FROM LigaHistorico lh WHERE lh.liga.activa = 1 ORDER BY lh.nombre")
	public Collection<LigaHistorico> findLigasActivas(); */
	
	//public Collection<LigaHistorico> findAllOrderByNombre();
}
