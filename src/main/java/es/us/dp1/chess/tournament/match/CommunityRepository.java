package es.us.dp1.chess.tournament.match;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CommunityRepository extends CrudRepository<Community, Integer>{
    List<Community> findAll();
}
