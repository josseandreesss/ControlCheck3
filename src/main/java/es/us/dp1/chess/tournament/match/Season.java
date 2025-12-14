package es.us.dp1.chess.tournament.match;


import java.time.LocalDate;
import java.util.List;

import es.us.dp1.chess.tournament.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "season")
public class Season extends BaseEntity {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    String name;

    @NotNull
    LocalDate startDate;

    @NotNull
    LocalDate endDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "season")
    List<ChessMatch> matches;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "season")
    List<Rating> ratings;

    @ManyToOne
    private Community community;
}
