package es.us.dp1.chess.tournament.match;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.us.dp1.chess.tournament.exceptions.AccessDeniedException;
import es.us.dp1.chess.tournament.exceptions.ResourceNotFoundException;
import es.us.dp1.chess.tournament.user.User;
import es.us.dp1.chess.tournament.user.UserService;


@RestController
@RequestMapping("/api/v1/seasons")
public class SeasonController {

    UserService userService;
    SeasonService seasonService;

    @Autowired
    public SeasonController(SeasonService service,UserService userService){
        this.seasonService=service;
        this.userService=userService;    
    }

    @GetMapping
    public List<Season> getAll() {
        User u = userService.findCurrentUser();
        List<Season> seasons = seasonService.getAll();
        if (u != null && u.hasAuthority("PLAYER")) {
            return seasons;
        }
        throw new AccessDeniedException();
    }

    @GetMapping("/{X}/standings")
    public List<Rating> getStandings(@PathVariable Integer X) {
        User u = null;
        Season s = seasonService.getById(X);
        
        if (s == null) {
            throw new ResourceNotFoundException("Season not found");
        }

        try {
            u = userService.findCurrentUser();
        } catch (Exception e) {

        }
        
        List<Rating> ratings = seasonService.getStandings(s);
        if (u != null && u.hasAuthority("PLAYER")) {
            return ratings;
        }
        throw new AccessDeniedException();
    }

}