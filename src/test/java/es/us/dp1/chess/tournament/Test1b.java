package es.us.dp1.chess.tournament;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import es.us.dp1.chess.tournament.match.Community;
import es.us.dp1.chess.tournament.match.Rating;
import es.us.dp1.chess.tournament.match.Season;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@DataJpaTest()
public class Test1b extends ReflexiveTest{

    @Autowired(required = false)
    EntityManager em;

    @Test
    public void test1bSeasonMatchAnnotations() {
        checkThatFieldIsAnnotatedWith(Season.class, "matches", OneToMany.class);        
    }

     @Test
    public void test1bCommunitySeasonAnnotations() {
        checkThatFieldIsAnnotatedWith(Community.class, "seasons", OneToMany.class);        
    }

    @Test
    public void test1bSeasonRatingAnnotations() {
        checkThatFieldIsAnnotatedWith(Season.class, "ratings", OneToMany.class);     
    }    

    @Test
    public void test1bRatingUserAnnotations() {        
        checkThatFieldIsAnnotatedWith(Rating.class, "player", ManyToOne.class);
        checkThatFieldIsAnnotatedWith(Rating.class, "player", NotNull.class);
    }    


}
