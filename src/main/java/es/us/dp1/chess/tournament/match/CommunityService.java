package es.us.dp1.chess.tournament.match;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.us.dp1.chess.tournament.exceptions.MalformedCommunityException;
import es.us.dp1.chess.tournament.user.User;

@Service
public class CommunityService {
    CommunityRepository repo;

    @Autowired
    public CommunityService(CommunityRepository repo) {
        this.repo = repo;
    }


    @Transactional(readOnly = true)
    public Optional<Community> getCommunityById(Integer id) {
        return repo.findById(id);
    }

    @Transactional(rollbackFor = MalformedCommunityException.class)
    public Community save(Community m) {
        List<User> members = m.getMembers();
        List<Season> seasons = m.getSeasons();
        Boolean todosCumplen = true;
        
        for(User member : members) {
            Integer partidasJugadas = 0;
            for (Season s : seasons) {
                for (ChessMatch cm : s.getMatches()) {
                    if(member.equals(cm.getBlackPlayer()) || member.equals(cm.getWhitePlayer())) {
                        partidasJugadas++;
                    }
                }
            }
            if (partidasJugadas < 2) {
                todosCumplen = false;
                break;
            }
        }

        if(!todosCumplen) {
            throw new MalformedCommunityException("Todos los miembros deben haber jugado 2 partidas");
        }
        if (members.size() > m.getMaxPlayers()) {
            throw new MalformedCommunityException("El numero de miembros no puede superar el máximo");
        }
        repo.save(m);
        return m;
    }
    
    

    @Transactional(readOnly = true)
    public List<Community> getCommunities() {
        return repo.findAll();
    }    
    }
