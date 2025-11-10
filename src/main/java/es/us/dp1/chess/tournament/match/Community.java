package es.us.dp1.chess.tournament.match;

import java.util.List;

import es.us.dp1.chess.tournament.model.NamedEntity;
import es.us.dp1.chess.tournament.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
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

    @Transient
    List<Season> seasons;
    
}
