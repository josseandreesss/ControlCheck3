package es.us.dp1.chess.tournament.match;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Community save(Community m) {
         // TODO: Change to solve exercise 3:
        return null;
    }
    
    

    @Transactional(readOnly = true)
    public List<Community> getCommunities() {
        return repo.findAll();
    }    
    }
