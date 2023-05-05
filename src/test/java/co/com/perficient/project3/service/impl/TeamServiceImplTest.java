package co.com.perficient.project3.service.impl;

import co.com.perficient.project3.model.entity.Stadium;
import co.com.perficient.project3.model.entity.Team;
import co.com.perficient.project3.repository.TeamRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class TeamServiceImplTest {

    @InjectMocks
    private TeamServiceImpl teamService;
    @Mock
    private TeamRepository teamRepository;

    final UUID ID_TEAM = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void create() {
        Team team = Team.builder().id(ID_TEAM).build();

        when(teamRepository.save(any(Team.class))).thenReturn(team);

        Team teamCreated = teamService.create(team);
        Assertions.assertThat(teamCreated).isNotNull();
    }

    @Test
    void findAll() {
        when(teamRepository.findAll()).thenReturn(Collections.singletonList(Team.builder().build()));

        List<Team> teams = teamService.findAll();
        Assertions.assertThat(teams).isNotNull().isNotEmpty();
    }

    @Test
    void findById() {
        when(teamRepository.findById(any(UUID.class))).thenReturn(Optional.of(Team.builder().id(ID_TEAM).build()));

        Optional<Team> optionalTeam = teamService.findById(ID_TEAM);
        Assertions.assertThat(optionalTeam).isNotNull().isPresent();
    }

    @Test
    void update() {
        Team oldTeam = Team.builder().build();
        final String NAME = "teamName";
        final String COUNTRY = "colombia";
        Team newTeam = Team.builder().name(NAME).country(COUNTRY).stadium(Stadium.builder().build()).build();

        when(teamRepository.saveAndFlush(any(Team.class))).thenReturn(oldTeam);

        Team teamUpdated = teamService.update(oldTeam, newTeam);
        Assertions.assertThat(teamUpdated).isNotNull();
        Assertions.assertThat(teamUpdated.getName()).isEqualTo(NAME);
        Assertions.assertThat(teamUpdated.getCountry()).isEqualTo(COUNTRY);
        Assertions.assertThat(teamUpdated.getStadium()).isNotNull();
    }

    @Test
    void delete() {
        teamService.delete(UUID.randomUUID());
        verify(teamRepository, times(1)).deleteById(any(UUID.class));
    }
}