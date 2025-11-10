package es.us.dp1.chess.tournament.match;


import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Season{
    Integer id;
    String name;
    LocalDate startDate;
    LocalDate endDate;

    @Transient
    List<ChessMatch> matches;

    @Transient
    List<Rating> ratings;
}
