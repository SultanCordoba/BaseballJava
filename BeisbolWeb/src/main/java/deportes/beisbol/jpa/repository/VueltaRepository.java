package deportes.beisbol.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import deportes.beisbol.jpa.model.Vuelta;

public interface VueltaRepository extends CrudRepository<Vuelta, Byte> {

}
