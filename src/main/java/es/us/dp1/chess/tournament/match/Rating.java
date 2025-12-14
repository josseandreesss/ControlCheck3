package es.us.dp1.chess.tournament.match;


import es.us.dp1.chess.tournament.model.BaseEntity;
import es.us.dp1.chess.tournament.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rating")
public class Rating extends BaseEntity {

    @NotNull
    @Min(100)
    Integer elo;

    @NotNull
    @Min(0)
    Integer wins;

    @NotNull
    @Min(0)
    Integer losses;

    @NotNull
    @Min(0)
    Integer draws;
    
    @Transient
    public Double getWinRate() {
        Double wins = this.getWins().doubleValue();
        Double losses = this.getLosses().doubleValue();
        Double totalGames = wins + losses;
        if(totalGames == 0.0) return 0.0;
        Double winRate = wins / totalGames;
        return winRate;
    }

    @ManyToOne
    @NotNull
    User player;

    @ManyToOne
    Season season;
}
