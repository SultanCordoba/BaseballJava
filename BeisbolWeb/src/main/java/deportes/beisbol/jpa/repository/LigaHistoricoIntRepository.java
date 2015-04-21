package deportes.beisbol.jpa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import deportes.beisbol.jpa.model.LigaHistoricoInt;

public interface LigaHistoricoIntRepository extends CrudRepository<LigaHistoricoInt, Short> {
	
	@Query("SELECT lhi FROM LigaHistoricoInt lhi WHERE lhi.ligaHistorico.siglas = :siglas AND lhi.idioma.abreviatura = :idioma")
	public Collection<LigaHistoricoInt> buscarSiglasIdioma(@Param("siglas") String siglas, @Param("idioma") String idioma);

	@Query("SELECT lhi FROM LigaHistoricoInt lhi WHERE lhi.ligaHistorico.liga.id = :id AND lhi.idioma.abreviatura = :idioma")
	public Collection<LigaHistoricoInt> buscarLigaId(@Param("id") Byte id, @Param("idioma") String idioma);
	
}
