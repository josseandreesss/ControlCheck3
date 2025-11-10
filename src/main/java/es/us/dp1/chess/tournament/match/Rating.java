package es.us.dp1.chess.tournament.match;

import es.us.dp1.chess.tournament.user.User;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rating {
    Integer elo;
    Integer wins;
    Integer losses;
    Integer draws;
    
    public Double getWinRate() {
        // TODO: Implement this method to solve Exercise 1a
        return 0.0;
    }

    @Transient
    User player;
}
