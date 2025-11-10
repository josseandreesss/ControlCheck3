package es.us.dp1.chess.tournament.match;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CommunityRepository extends CrudRepository<Community, Integer>{
    List<Community> findAll();
}
