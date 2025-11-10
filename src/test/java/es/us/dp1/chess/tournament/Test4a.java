
package es.us.dp1.chess.tournament;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.FilterType;

import es.us.dp1.chess.tournament.match.ChessBoard;
import es.us.dp1.chess.tournament.match.ChessMatch;
import es.us.dp1.chess.tournament.match.Rating;
import es.us.dp1.chess.tournament.match.Season;
import es.us.dp1.chess.tournament.match.SeasonRepository;
import es.us.dp1.chess.tournament.match.SeasonService;
import es.us.dp1.chess.tournament.user.Authorities;
import es.us.dp1.chess.tournament.user.User;
import jakarta.persistence.EntityManager;

@ExtendWith(MockitoExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SeasonService.class))
public class Test4a extends ReflexiveTest{

    @Autowired
    SeasonRepository pr;

    @Autowired SeasonService ms;

    @Autowired
    EntityManager em;
        

    @Test
    void test4aSeasonServiceIsAnnotatedWithService() {
        assertTrue(SeasonService.class.isAnnotationPresent(Service.class),
                "SeasonService must be annotated with @Service");
    }
    
    
    @Test
    void test4aSeasonServiceClassShouldNotBeAnnotatedWithTransactional() {
        assertFalse(SeasonService.class.isAnnotationPresent(Transactional.class),
                "@Transactional must not be applied at class level");
    }

    @Test
    void test4aRequiredPublicMethodsOfSeasonServiceShouldBeTransactional() throws Exception {
        Method m1 = SeasonService.class.getMethod("getStandings", Season.class);
        Method m2 = SeasonService.class.getMethod("getAll");
        Method m3 = SeasonService.class.getMethod("getById", Integer.class);
        Method m4 = SeasonService.class.getMethod("save", Season.class);

        List<Method> methods = List.of(m1, m2, m3, m4);
        for (Method m : methods) {
            assertTrue(m.isAnnotationPresent(Transactional.class),
                    "Method " + m.getName() + " must be annotated with @Transactional");
        }
    }

    @Test
    void test4aSeasonRepositoryShouldBeASpringDataRepository() {
        boolean isSpringDataRepo =
                Repository.class.isAssignableFrom(SeasonRepository.class) ||
                JpaRepository.class.isAssignableFrom(SeasonRepository.class);

        assertTrue(isSpringDataRepo,
                "SeasonRepository must be a Spring Data repository (e.g., extend JpaRepository)");
        // Optional: check for correct generic parameters <Season, Integer>
        if (JpaRepository.class.isAssignableFrom(SeasonRepository.class)) {
            boolean genericsOk = false;
            for (var i : SeasonRepository.class.getGenericInterfaces()) {
                if (i instanceof ParameterizedType pt &&
                    pt.getRawType() instanceof Class<?> raw &&
                    JpaRepository.class.isAssignableFrom(raw)) {
                    var args = pt.getActualTypeArguments();
                    if (args.length == 2 &&
                        args[0].getTypeName().endsWith(".Season") &&
                        (args[1].getTypeName().endsWith(".Integer") ||
                         args[1].getTypeName().equals("java.lang.Integer"))) {
                        genericsOk = true; break;
                    }
                }
            }
            assertTrue(genericsOk, "JpaRepository must be parameterized as JpaRepository<Season, Integer>");
        }
    }

    @Test
    void test4aSeasonServiceGetStandings() {
        Season s = createValidSeason();
        s.setId(2);
        Rating r1 = createValidRating(); r1.setElo(2080);
        Rating r2 = createValidRating(); r2.setElo(1310);
        Rating r3 = createValidRating(); r3.setElo(1567);
        s.setRatings(List.of(r1, r2, r3));
        
        List<Rating> standings = ms.getStandings(s);
        assertNotNull(standings, "getStandings must not return null");
        assertEquals(3, standings.size());
        assertEquals(2080, standings.get(0).getElo());
        assertEquals(1567, standings.get(1).getElo());
        assertEquals(1310, standings.get(2).getElo());
    }

    @Test
    void test4aSeasonServiceGetAllDelegatesToRepository() {
        SeasonRepository repository = mock(SeasonRepository.class);
        SeasonService service = new SeasonService(Optional.of(repository));
        var p1 = mock(Season.class);
        when(repository.findAll()).thenReturn(List.of(p1));

        List<Season> result = service.getAll();

        assertEquals(1, result.size());
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void test4aSeasonServiceGetByIdDelegatesToRepository() {
        SeasonRepository repository = mock(SeasonRepository.class);
        SeasonService service = new SeasonService(Optional.of(repository));
        var season = mock(Season.class);
        when(repository.findById(42)).thenReturn(Optional.of(season));

        Season result = service.getById(42);

        assertTrue(result!=null );
        assertSame(season, result);
        verify(repository).findById(42);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void test4aSeasonServiceSaveDelegatesToRepository() {
        SeasonRepository repository = mock(SeasonRepository.class);
        SeasonService service = new SeasonService(Optional.of(repository));
        var season = mock(Season.class);
        when(repository.save(season)).thenReturn(season);

        var saved = service.save(season);

        assertSame(season, saved);
        verify(repository).save(season);
        verifyNoMoreInteractions(repository);
    }

public static Rating createValidRating(){
        Rating r = new Rating();
        User p = createUser("Test Player");
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
     public static Season createValidSeason(){
        Season s = new Season();
        s.setName( "Autumn Season");
        s.setMatches(List.of(createMatch()));
        s.setRatings(List.of(createValidRating()));
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
