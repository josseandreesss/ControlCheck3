package es.us.dp1.chess.tournament.match;


import es.us.dp1.chess.tournament.user.UserService;



public class SeasonController {

    UserService userService;
    SeasonService seasonService;


    public SeasonController(SeasonService service,UserService userService){
        this.seasonService=service;
        this.userService=userService;    
    }

}