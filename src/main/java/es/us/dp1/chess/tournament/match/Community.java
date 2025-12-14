package es.us.dp1.chess.tournament.match;

import java.util.List;

import es.us.dp1.chess.tournament.model.NamedEntity;
import es.us.dp1.chess.tournament.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Community extends NamedEntity {

    String location;
    Integer maxPlayers;

    @ManyToMany
    List<User> members;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "community")
    @NotNull
    List<Season> seasons;
    
}
