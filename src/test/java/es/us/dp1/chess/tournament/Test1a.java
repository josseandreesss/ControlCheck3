package es.us.dp1.chess.tournament;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import es.us.dp1.chess.tournament.match.SeasonRepository;
import es.us.dp1.chess.tournament.match.ChessBoard;
import es.us.dp1.chess.tournament.match.ChessMatch;
import es.us.dp1.chess.tournament.match.Rating;
import es.us.dp1.chess.tournament.match.RatingRepository;
import es.us.dp1.chess.tournament.match.Season;
import es.us.dp1.chess.tournament.user.Authorities;
import es.us.dp1.chess.tournament.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

@DataJpaTest()
public class Test1a extends ReflexiveTest{

    @Autowired(required = false)
    SeasonRepository seasonRepository;

    @Autowired(required = false)
    RatingRepository ratingRepository;

    @Autowired
    EntityManager em;

    @Test
    public void test1aRepositoriesExist(){
        assertNotNull(ratingRepository,"The Rating repository was not injected into the tests, its autowired value was null");
        assertNotNull(seasonRepository,"The Season repository was not injected into the tests, its autowired value was null");

        test1aRatingRepositoryContainsMethod();
        test1aSeasonRepositoryContainsMethod();
    }

    public void test1aRatingRepositoryContainsMethod(){
        if(ratingRepository!=null){
            Object v=ratingRepository.findById(12);
            assertFalse(null!=v && ((Optional)v).isPresent(), "No result (null) should be returned for a Rating that does not exist");
        }else
            fail("The Season repository was not injected into the tests, its autowired value was null");
    }

    public void test1aSeasonRepositoryContainsMethod(){
        if(seasonRepository!=null){
            Object v=seasonRepository.findById(12);
            assertFalse(null!=v && ((Optional)v).isPresent(), "No result (null) should be returned for a Season that does not exist");
        }else
            fail("The Season repository was not injected into the tests, its autowired value was null");
    }




    @Test
    public void test1aCheckSeasonConstraints() {
         Map<String,List<Object>> invalidValues = Map.of(
            "name", List.of(
                "      ",
                "a",
                "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor. Una olla de algo más vaca que carnero, salpicón las más noches, duelos y quebrantos los sábados, lentejas los viernes, algún palomino de añadidura los domingos, consumían las tres partes de su hacienda. ")
            );

        Season s=createValidSeason(em);
        em.persist(s);

        checkThatFieldsAreMandatory(s, em, "name","startDate","endDate");

        checkThatValuesAreNotValid(s, invalidValues,em);
    }


    @Test
    public void test1aCheckRatingContraints() {
         Map<String,List<Object>> invalidValues=Map.of(
                                            "elo", List.of(-1,99),
                                            "wins", List.of(-100,-1),
                                            "losses", List.of(-100,-1),
                                            "draws", List.of(-100,-1)
                                            );

        Rating t=createValidRating(em);
        em.persist(t);

        checkThatFieldsAreMandatory(t, em, "elo","wins","losses","draws");

        checkThatValuesAreNotValid(t, invalidValues,em);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 10, 0.0",
        "10, 0, 1.0",
        "4, 6, 0.4"
    })
    public void test1aCheckGetWinRateCalculation(Integer wins, Integer losses, Double expected) {
        Rating t=createValidRating(em);
        t.setWins(wins);
        t.setLosses(losses);
        Double winRate = t.getWinRate();
        assertEquals(expected, winRate);
    }

    @Test
    public void test1aCheckGetWinRateConstraints() {
        checkTransient(Rating.class, "getWinRate");
    }

    @Test
    public void test1aCheckSeasonAnnotations() throws NoSuchFieldException, SecurityException {
        assertTrue(classIsAnnotatedWith(Season.class,Entity.class));        
    }
    @Test
    public void test1aCheckRatingAnnotations() throws NoSuchFieldException, SecurityException {
        assertTrue(classIsAnnotatedWith(Rating.class,Entity.class));        
    }


    public static Rating createValidRating(EntityManager em){
        Rating r = new Rating();
        User p = createUser("Test Player");
        em.persist(p.getAuthority());
        em.persist(p);
        r.setElo(1200);
        r.setWins(2);
        r.setLosses(2);
        r.setDraws(2);
        r.setPlayer(p);            
        return r;
    }

    private static ChessMatch createMatch() {
        ChessMatch match=new ChessMatch();
        match.setName("Test Match");
        match.setStart( LocalDateTime.of(2025, 11, 1, 12, 0));
        match.setFinish( LocalDateTime.of(2025, 11, 1, 13, 0));
        match.setTurnDuration(60L);
        match.setWhitePlayer(createUser("José Maestre"));
        match.setBlackPlayer(createUser("Gary Kasparov"));
        match.setWinner(null);
        match.setBoard(new ChessBoard());
        return match;
    }

    public static Season createValidSeason(EntityManager em){
        Season s = new Season();
        s.setName( "Autumn Season");
        s.setMatches(List.of(createMatch()));
        s.setRatings(List.of(createValidRating(em)));
        s.setStartDate( LocalDate.of(2025, 9, 20));
        s.setEndDate(LocalDate.of(2025, 12, 21));
        return s;
    }


    public static User createUser(String name){
        Authorities a1=new Authorities();
        a1.setAuthority("PANA");
        User u1=new User();
        setValue(u1,"username",String.class,name);
        setValue(u1, "authority", Authorities.class, a1);
        return u1;
    }

}
