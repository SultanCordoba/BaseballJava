package deportes.beisbol.jpa.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import deportes.beisbol.jpa.model.LigaHistorico;
import deportes.beisbol.jpa.model.Temporada;

public interface TemporadaRepository extends JpaRepository<Temporada, Short> {
	public Collection<Temporada> findByLigaHistorico(LigaHistorico liga);
	
	public Collection<Temporada> findByLigaHistoricoOrderByFechaInicioDesc(LigaHistorico liga);
	
	public Temporada findByNombreAndLigaHistorico(String nombre, LigaHistorico liga);
}
