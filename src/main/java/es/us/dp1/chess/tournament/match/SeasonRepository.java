package es.us.dp1.chess.tournament.match;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SeasonRepository extends CrudRepository<Season, Integer> {
  
    List<Season> findAll();

    Optional<Season> findById(Integer i);

    Season save(Season season);
}
