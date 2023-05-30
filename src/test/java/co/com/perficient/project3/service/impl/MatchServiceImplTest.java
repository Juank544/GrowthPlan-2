package co.com.perficient.project3.service.impl;

import co.com.perficient.project3.model.entity.Match;
import co.com.perficient.project3.model.entity.Stadium;
import co.com.perficient.project3.model.entity.Team;
import co.com.perficient.project3.repository.MatchRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MatchServiceImplTest {

    @InjectMocks
    private MatchServiceImpl matchService;
    @Mock
    private MatchRepository matchRepository;

    final UUID ID_MATCH = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void create() {
        Match match = Match.builder().id(ID_MATCH).build();

        when(matchRepository.save(any(Match.class))).thenReturn(match);

        Match matchCreated = matchService.create(match);
        assertNotNull(matchCreated);
        assertEquals(ID_MATCH, matchCreated.getId());
    }

    @Test
    void findAll() {
        when(matchRepository.findAll()).thenReturn(Collections.singletonList(Match.builder().build()));

        List<Match> matches = matchService.findAll();
        Assertions.assertThat(matches).isNotNull().isNotEmpty();
    }

    @Test
    void findById() {
        Match match = Match.builder().id(ID_MATCH).build();

        when(matchRepository.findById(any(UUID.class))).thenReturn(Optional.of(match));

        Optional<Match> optionalMatch = matchService.findById(ID_MATCH);
        assertNotNull(optionalMatch);
        assertEquals(match, optionalMatch.get());
        Assertions.assertThat(optionalMatch).isPresent();
    }

    @Test
    void update() {
        Match oldMatch = Match.builder().build();
        final String ROUND = "lAST 16";
        final String SCORE = "1-0";
        Match newMatch = Match.builder().date(LocalDate.now()).stadium(Stadium.builder().build()).round(ROUND)
                .score(SCORE).homeTeam(Team.builder().name("homeTeam").build())
                .awayTeam(Team.builder().name("awayTeam").build()).build();

        when(matchRepository.saveAndFlush(any(Match.class))).thenReturn(oldMatch);

        Match matchUpdated = matchService.update(oldMatch, newMatch);
        assertNotNull(matchUpdated);
        assertNotNull(matchUpdated.getDate());
        assertNotNull(matchUpdated.getStadium());
        assertEquals(ROUND, matchUpdated.getRound());
        assertEquals(SCORE, matchUpdated.getScore());
        assertEquals("homeTeam", matchUpdated.getHomeTeam().getName());
        assertEquals("awayTeam", matchUpdated.getAwayTeam().getName());
    }

    @Test
    void delete() {
        matchService.delete(UUID.randomUUID());
        verify(matchRepository, times(1)).deleteById(any(UUID.class));
    }
}