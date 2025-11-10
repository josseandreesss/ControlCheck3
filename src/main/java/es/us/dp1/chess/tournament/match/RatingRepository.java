package es.us.dp1.chess.tournament.match;

import java.util.Optional;

public interface RatingRepository {

    Optional<Rating> findById(int i);
    
}
