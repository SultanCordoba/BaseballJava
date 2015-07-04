package deportes.beisbol.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import deportes.beisbol.jpa.model.Roster;

public interface RosterRepository extends JpaRepository<Roster, Short>,
   QueryDslPredicateExecutor<Roster> {
}
