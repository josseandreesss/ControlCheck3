package es.us.dp1.chess.tournament.match;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer>{

    Optional<Rating> findById(int i);
    
}
