package es.us.dp1.chess.tournament.match;

import java.util.List;
import java.util.Optional;

public class SeasonService {

    SeasonRepository repository;

    public SeasonService(Optional<SeasonRepository> repository) {
        this.repository = repository.isPresent()?repository.get():null;
    }

    public List<Rating> getStandings(Season s) {
        // TODO: implement this method to solve Exercise 4a
        return null;
    }

    public List<Season> getAll() {
        // TODO: implement this method to solve Exercise 4a
        return null;
    }

    public Season getById(Integer i) {
        // TODO: implement this method to solve Exercise 4a
        return null;
    }

    public Season save(Season season) {
        // TODO: implement this method to solve Exercise 4a
        return null;
    }
    
}
