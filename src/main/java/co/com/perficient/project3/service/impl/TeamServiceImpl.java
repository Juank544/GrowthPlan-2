package co.com.perficient.project3.service.impl;

import co.com.perficient.project3.model.entity.Team;
import co.com.perficient.project3.repository.TeamRepository;
import co.com.perficient.project3.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team create(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> findById(UUID id) {
        return teamRepository.findById(id);
    }

    @Override
    public Team update(Team oldTeam, Team newTeam) {
        oldTeam.setName(newTeam.getName());
        oldTeam.setCountry(newTeam.getCountry());
        oldTeam.setStadium(newTeam.getStadium());
        return teamRepository.saveAndFlush(oldTeam);
    }

    @Override
    public void delete(UUID id) {
        teamRepository.deleteById(id);
    }
}
