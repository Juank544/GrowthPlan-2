package co.com.perficient.project3.service.impl;

import co.com.perficient.project3.model.entity.Match;
import co.com.perficient.project3.repository.MatchRepository;
import co.com.perficient.project3.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public Match create(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    @Override
    public Optional<Match> findById(UUID id) {
        return matchRepository.findById(id);
    }

    @Override
    public Match update(Match oldMatch, Match newMatch) {
        oldMatch.setDate(newMatch.getDate());
        oldMatch.setStadium(newMatch.getStadium());
        oldMatch.setRound(newMatch.getRound());
        oldMatch.setStatus(newMatch.getStatus());
        oldMatch.setScore(newMatch.getScore());
        oldMatch.setHomeTeam(newMatch.getHomeTeam());
        oldMatch.setAwayTeam(newMatch.getAwayTeam());
        return matchRepository.saveAndFlush(oldMatch);
    }

    @Override
    public void delete(UUID id) {
        matchRepository.deleteById(id);
    }
}
