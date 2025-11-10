package es.us.dp1.chess.tournament.match;

import java.util.List;
import java.util.Optional;

public interface SeasonRepository {
  
    List<Season> findAll();

    Optional<Season> findById(Integer i);

    Season save(Season season);
}
