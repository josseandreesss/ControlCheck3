package es.us.dp1.chess.tournament;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.us.dp1.chess.tournament.exceptions.MalformedCommunityException;
import es.us.dp1.chess.tournament.match.ChessBoard;
import es.us.dp1.chess.tournament.match.ChessMatch;
import es.us.dp1.chess.tournament.match.Community;
import es.us.dp1.chess.tournament.match.CommunityRepository;
import es.us.dp1.chess.tournament.match.CommunityService;
import es.us.dp1.chess.tournament.match.Season;
import es.us.dp1.chess.tournament.user.Authorities;
import es.us.dp1.chess.tournament.user.User;


@ExtendWith(MockitoExtension.class)
public class Test3 extends ReflexiveTest{
    @Mock
    CommunityRepository cr;

    CommunityService cs;

    @BeforeEach
    public void configuation(){
        cs = new CommunityService(cr);
    }

    @Test
    public void test3CheckTransactionalityOfMatchService(){
        checkTransactionalRollbackFor(CommunityService.class,"save", MalformedCommunityException.class,Community.class);        
    }    

     
    @Test
    public void test3CommunityServiceCanSaveCommunitiesWithValidPlayersAndMax() throws MalformedCommunityException {        
        when(cr.save(any(Community.class))).thenReturn(null);
        User p1 = createUser("Player 1");
        p1.setId(21);
        User p2 = createUser("Player 2");
        p2.setId(22);
        Community c = createCommunity();
        ChessMatch m1 = createMatch(p1, p2);
        ChessMatch m2 = createMatch(p2, p1);
        Season s = createSeason();
        s.setMatches(List.of(m1, m2));
        c.setSeasons(List.of(s));
        c.setMembers(List.of(p1, p2));
        
        cs.save(c);
        // The test fails if the service does not invoke the save function of the repository:
        verify(cr).save(c);
    } 

    @Test
    public void test3CommunityServiceCanSaveCommunitiesWithValidPlayersAndMaxAndMoreThanOneSeason() throws MalformedCommunityException {        
        when(cr.save(any(Community.class))).thenReturn(null);
        User p1 = createUser("Player 1");
        p1.setId(21);
        User p2 = createUser("Player 2");
        p2.setId(22);
        Community c = createCommunity();
        ChessMatch m1 = createMatch(p1, p2);
        ChessMatch m2 = createMatch(p2, p1);
        Season s1 = createSeason();
        Season s2 = createSeason();
        s2.setMatches(List.of(m1, m2));
        c.setSeasons(List.of(s1, s2));
        c.setMembers(List.of(p1, p2));
        
        cs.save(c);
        // The test fails if the service does not invoke the save function of the repository:
        verify(cr).save(c);
    } 

        
    @Test
    public void test3CommunityServiceCanNotSaveCommunitiesWithInvalidPlayers() throws MalformedCommunityException {
        lenient().when(cr.save(any(Community.class))).thenReturn(null);
        User p1 = createUser("Player 1");
        p1.setId(21);
        User p2 = createUser("Player 2");
        p2.setId(22);
        User p3 = createUser("Player 3");
        p3.setId(23);
        Community c = createCommunity();
        ChessMatch m1 = createMatch(p1, p2);
        ChessMatch m2 = createMatch(p2, p1);
        Season s = createSeason();
        s.setMatches(List.of(m1, m2));
        c.setSeasons(List.of(s));
        c.setMembers(List.of(p1, p2, p3)); // p3 is not in any match
        // The test fails if the service does not throw the exception;
        assertThrows(MalformedCommunityException.class, ()->{
            cs.save(c);
        });                
        // The test fails if the service invokes the save function of the repository:
        verify(cr,never()).save(c);
    } 

     @Test
    public void test3CommunityServiceCanNotSaveCommunitiesWithTooMuchPlayers() throws MalformedCommunityException {
        lenient().when(cr.save(any(Community.class))).thenReturn(null);
        User p1 = createUser("Player 1");
        p1.setId(21);
        User p2 = createUser("Player 2");
        p2.setId(22);
        Community c = createCommunity();
        ChessMatch m1 = createMatch(p1, p2);
        ChessMatch m2 = createMatch(p2, p1);
        Season s = createSeason();
        s.setMatches(List.of(m1, m2));
        c.setSeasons(List.of(s));
        c.setMaxPlayers(1);
        c.setMembers(List.of(p1, p2)); // too many players
        // The test fails if the service does not throw the exception;
        assertThrows(MalformedCommunityException.class, ()->{
            cs.save(c);
        });                
        // The test fails if the service invokes the save function of the repository:
        verify(cr,never()).save(c);
    } 

    private static Community createCommunity() {
        Community c = new Community();

        c.setName("Test Community");
        c.setLocation("Test Location");
        c.setMaxPlayers(5);

        return c;
    }

    public static Season createSeason() {
        Season s = new Season();
        s.setName( "Test Season");
        s.setStartDate( LocalDateTime.of(2025, 10, 1, 0, 0).toLocalDate());
        s.setEndDate( LocalDateTime.of(2025, 12, 31, 0, 0).toLocalDate());
        s.setMatches(List.of());
        return s;
    }

    private static ChessMatch createMatch(User whitePlayer, User blackPlayer) {
        ChessMatch match=new ChessMatch();
        match.setName("Test Match");
        match.setStart( LocalDateTime.of(2025, 11, 1, 12, 0));
        match.setFinish( LocalDateTime.of(2025, 11, 1, 13, 0));
        match.setTurnDuration(60L);
        match.setWhitePlayer(whitePlayer);
        match.setBlackPlayer(blackPlayer);
        match.setWinner(null);
        match.setBoard(new ChessBoard());
        return match;
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
