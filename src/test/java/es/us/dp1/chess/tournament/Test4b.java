package es.us.dp1.chess.tournament;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import com.fasterxml.jackson.core.JsonProcessingException;

import es.us.dp1.chess.tournament.match.Rating;
import es.us.dp1.chess.tournament.match.Season;
import es.us.dp1.chess.tournament.match.SeasonService;
import es.us.dp1.chess.tournament.user.Authorities;
import es.us.dp1.chess.tournament.user.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class Test4b extends ReflexiveTest{
    @Autowired
	private WebApplicationContext context;

    @MockBean
    SeasonService seasonService;

	private MockMvc mockMvc;

    private String url = "/api/v1/seasons";
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
		.webAppContextSetup(context)
		.apply(SecurityMockMvcConfigurers.springSecurity())
		.build();
	}



    @Test
    @Transactional
    @WithMockUser(username = "player1", authorities = {"PLAYER"})
    public void test4bCanAllSeasons() throws JsonProcessingException, Exception{
        when(seasonService.getAll()).thenReturn(List.of(createSeason(),createSeason())); 
        mockMvc.perform(get(url))
			.andExpect(status().isOk())            
            .andExpect(jsonPath("$", hasSize(2)));
        
        verify(seasonService).getAll();
    }    

    @Test
    @Transactional
    @WithMockUser(username = "player2", authorities = {"PLAYER"})
    public void test4bCanGetASpecificSeasonStandings() throws JsonProcessingException, Exception{
        Season s = createSeason();
        Rating r = createValidRating();
        s.setRatings(List.of(r));
        when(seasonService.getById(500)).thenReturn(s);
        when(seasonService.getStandings(s)).thenReturn(List.of(r));

        mockMvc.perform(get(url+"/500/standings"))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].elo", is(1200)));

        verify(seasonService).getById(500);
        verify(seasonService).getStandings(s);
    }    

    

    @Test
    @Transactional
    @WithMockUser(username = "player1", authorities = {"PLAYER"})
    public void test4bCannotGetNonExistentSeason() throws JsonProcessingException, Exception{
        when(seasonService.getById(600)).thenReturn(null);
        mockMvc.perform(get(url+"/600/standings"))
			.andExpect(status().isNotFound());
    }

    
    @Test
    @Transactional    
    public void test4bCannotGetSeasonIfYouAreNotPlayer() throws JsonProcessingException, Exception{
        Season s = createSeason();
        Rating r = createValidRating();
        s.setRatings(List.of(r));
        when(seasonService.getById(500)).thenReturn(s);
        when(seasonService.getStandings(s)).thenReturn(List.of(r));
       // Perform request as ADMIN
        mockMvc.perform(get(url + "/500/standings")
            .with(user("admin").authorities(new SimpleGrantedAuthority("ADMIN"))))
            .andExpect(status().isForbidden());

        // Perform request as PLAYER
        mockMvc.perform(get(url + "/500/standings")
            .with(user("player1").authorities(new SimpleGrantedAuthority("PLAYER"))))
            .andExpect(status().isOk());
    }
    
    public static Season createSeason() {
        Season s = new Season();
        s.setName( "Test Season");
        s.setStartDate( LocalDateTime.of(2025, 10, 1, 0, 0).toLocalDate());
        s.setEndDate( LocalDateTime.of(2025, 12, 31, 0, 0).toLocalDate());
        s.setMatches(List.of());
        return s;
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

    public static User createUser(String name){
        Authorities a1=new Authorities();
        a1.setAuthority("PANA");
        User u1=new User();
        setValue(u1,"username",String.class,name);
        setValue(u1, "authority", Authorities.class, a1);
        return u1;
    }

}
