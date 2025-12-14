package es.us.dp1.chess.tournament.match;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.us.dp1.chess.tournament.exceptions.ResourceNotFoundException;

@Service
public class SeasonService {

    SeasonRepository repository;

    public SeasonService(Optional<SeasonRepository> repository) {
        this.repository = repository.isPresent()?repository.get():null;
    }

    @Transactional(readOnly = true)
    public List<Rating> getStandings(Season s) {
        List<Rating> standings = s.getRatings().stream().sorted(Comparator.comparingInt(Rating::getElo).reversed()).collect(Collectors.toList());
        return standings;
    }

    @Transactional(readOnly = true)
    public List<Season> getAll() {
        List<Season> seasons = repository.findAll();
        return seasons;
    }

    @Transactional(readOnly = true)
    public Season getById(Integer i) {
        Season season = repository.findById(i).orElseThrow(() -> new ResourceNotFoundException("SeasonId"));
        return season;
    }

    @Transactional
    public Season save(Season season) {
        repository.save(season);
        return season;
    }
    
}
