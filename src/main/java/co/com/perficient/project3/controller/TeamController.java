package co.com.perficient.project3.controller;

import co.com.perficient.project3.mapper.TeamMapper;
import co.com.perficient.project3.model.dto.TeamDTO;
import co.com.perficient.project3.model.entity.Team;
import co.com.perficient.project3.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static co.com.perficient.project3.utils.constant.TeamConstants.TEAM_ENDPOINT;

@RestController
@RequestMapping(value = TEAM_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public class TeamController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamMapper teamMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {
        Team team = teamService.create(teamMapper.toEntity(teamDTO));
        return new ResponseEntity<>(teamMapper.toDTO(team), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> findAllTeams() {
        List<TeamDTO> teams = teamService.findAll().stream().map(teamMapper::toDTO).toList();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> findTeamById(@PathVariable UUID id) {
        Optional<Team> optionalTeam = teamService.findById(id);
        return optionalTeam.map(team -> new ResponseEntity<>(teamMapper.toDTO(team), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable UUID id, @RequestBody TeamDTO teamDTO) {
        Optional<Team> optionalTeam = teamService.findById(id);
        if (optionalTeam.isPresent()) {
            Team team = teamService.update(optionalTeam.get(), teamMapper.toEntity(teamDTO));
            return new ResponseEntity<>(teamMapper.toDTO(team), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeamDTO> deleteTeam(@PathVariable UUID id) {
        teamService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
