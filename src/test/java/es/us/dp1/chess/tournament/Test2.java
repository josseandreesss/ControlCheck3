package es.us.dp1.chess.tournament;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import es.us.dp1.chess.tournament.match.Community;
import es.us.dp1.chess.tournament.match.Rating;
import es.us.dp1.chess.tournament.match.Season;
import jakarta.persistence.EntityManager;

@DataJpaTest()
public class Test2 extends ReflexiveTest {

    @Autowired
    EntityManager em;

    // ---------- Seasons ----------
    @Test
    public void test2InitialSeason1() {
        Season t = em.find(Season.class, 1);
        assertNotNull(t, "There should exist a Season with id:1");

        assertEquals("Juegos Municipales 2024-25",
                t.getName());
        assertEquals(LocalDate.of(2024, 9, 1),
                t.getStartDate());
        assertEquals(LocalDate.of(2025, 5, 31),
                t.getEndDate());
    }

    @Test
    public void test2InitialSeason2() {
        Season t = em.find(Season.class, 2);
        assertNotNull(t, "There should exist a Season with id:2");

        assertEquals("Temporada Oficial 2025-26",
                t.getName());
        assertEquals(LocalDate.of(2025, 10, 15),
                t.getStartDate());
        assertEquals(LocalDate.of(2026, 6, 15),
                t.getEndDate());
    }


    // ---------- Ratings ----------

    @Test
    public void test2InitialRating1() {
        Rating m = em.find(Rating.class, 1);
        assertNotNull(m, "There should exist a Rating with id:1");

        assertEquals(1560, m.getElo());

        assertEquals(4, m.getWins());
        assertEquals(2, m.getLosses());
        assertEquals(1, m.getDraws());
    }

    @Test
    public void test2InitialRating2() {
        Rating m = em.find(Rating.class, 2);
        assertNotNull(m, "There should exist a Rating with id:2");

        assertEquals(1250, m.getElo());

        assertEquals(2, m.getWins());
        assertEquals(2, m.getLosses());
        assertEquals(0, m.getDraws());
    }

    @Test
    public void test2InitialRating3() {
        Rating m = em.find(Rating.class, 3);
        assertNotNull(m, "There should exist a Rating with id:3");

        assertEquals(1000, m.getElo());

        assertEquals(0, m.getWins());
        assertEquals(0, m.getLosses());
        assertEquals(4, m.getDraws());
    }

    @Test
    public void test2InitialRating4() {
        Rating m = em.find(Rating.class, 4);
        assertNotNull(m, "There should exist a Rating with id:4");

        assertEquals(2080, m.getElo());

        assertEquals(8, m.getWins());
        assertEquals(1, m.getLosses());
        assertEquals(2, m.getDraws());
    }

    @Test
    public void test2InitialRating5() {
        Rating m = em.find(Rating.class, 5);
        assertNotNull(m, "There should exist a Rating with id:5");

        assertEquals(1310, m.getElo());

        assertEquals(4, m.getWins());
        assertEquals(6, m.getLosses());
        assertEquals(1, m.getDraws());
    }

    @Test
    public void test2InitialRating6() {
        Rating m = em.find(Rating.class, 6);
        assertNotNull(m, "There should exist a Rating with id:6");

        assertEquals(1567, m.getElo());

        assertEquals(2, m.getWins());
        assertEquals(0, m.getLosses());
        assertEquals(0, m.getDraws());
    }

      
    // -------------------------
    // Season -> Matches
    // -------------------------

    @Test
    public void test2SeasonMatchesLinks() {        
        String matchesGetter = "getMatches";

        // Season 1 -> contains match 1, 2, and 4
        checkContainsById(Season.class, 1, matchesGetter, 1, em);
        checkContainsById(Season.class, 1, matchesGetter, 2, em);
        checkContainsById(Season.class, 1, matchesGetter, 3, em);

        // Season 2 -> contains matches 4, 5, and 6
        checkContainsById(Season.class, 2, matchesGetter, 4, em);
        checkContainsById(Season.class, 2, matchesGetter, 5, em);
        checkContainsById(Season.class, 2, matchesGetter, 6, em);
    }

    // -------------------------
    // Season -> Matches
    // -------------------------

    @Test
    public void test2SeasonRatingsLinks() {        
        String ratingsGetter = "getRatings";

        // Season 1 -> contains match 1, 2, and 4
        checkContainsById(Season.class, 1, ratingsGetter, 1, em);
        checkContainsById(Season.class, 1, ratingsGetter, 2, em);
        checkContainsById(Season.class, 1, ratingsGetter, 3, em);

        // Season 2 -> contains matches 4, 5, and 6
        checkContainsById(Season.class, 2, ratingsGetter, 4, em);
        checkContainsById(Season.class, 2, ratingsGetter, 5, em);
        checkContainsById(Season.class, 2, ratingsGetter, 6, em);
    }

    // -------------------------
    // Community -> Season
    // -------------------------

    @Test
    public void test2CommunitySeasonLinks() {
        
        String seasonGetter = "getSeasons";

        // Community 1 -> Seasons 1 and 2
        checkContainsById(Community.class, 1, seasonGetter, 1, em);
        checkContainsById(Community.class, 1, seasonGetter, 2, em);
    }


    // -------------------------
    // Rating -> User (player)
    // -------------------------

    @Test
    public void test2RatingUserLinks() {        
        String playerGetter = "getPlayer";

        // Rating 1 -> User 4
        checkLinkedById(Rating.class, 1, playerGetter, 4, em);
        // Rating 2 -> User 5
        checkLinkedById(Rating.class, 2, playerGetter, 5, em);
        // Rating 3 -> User 6
        checkLinkedById(Rating.class, 3, playerGetter, 6, em);
        // Rating 4 -> User 4
        checkLinkedById(Rating.class, 4, playerGetter, 4, em);
        // Rating 5 -> User 5
        checkLinkedById(Rating.class, 5, playerGetter, 5, em);
        // Rating 6 -> User 6
        checkLinkedById(Rating.class, 6, playerGetter, 6, em);
    }
   
}