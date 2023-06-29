package co.com.perficient.project3.service.impl;

import co.com.perficient.project3.model.entity.Standing;
import co.com.perficient.project3.repository.StandingRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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

class StandingServiceImplTest {

    @InjectMocks
    private StandingServiceImpl standingService;
    @Mock
    private StandingRepository standingRepository;

    final UUID ID_STANDING = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void create() {
        Standing standing = Standing.builder().id(ID_STANDING).build();

        when(standingRepository.save(any(Standing.class))).thenReturn(standing);

        Standing standingCreated = standingService.create(standing);
        assertNotNull(standingCreated);
        assertEquals(ID_STANDING, standingCreated.getId());
    }

    @Test
    void findAll() {
        when(standingRepository.findAll()).thenReturn(Collections.singletonList(Standing.builder().build()));

        List<Standing> standings = standingService.findAll();
        Assertions.assertThat(standings).isNotNull().isNotEmpty();
    }

    @Test
    void findById() {
        Standing standing = Standing.builder().id(ID_STANDING).build();

        when(standingRepository.findById(any(UUID.class))).thenReturn(Optional.of(standing));

        Optional<Standing> optionalStanding = standingService.findById(ID_STANDING);
        assertNotNull(optionalStanding);
        assertEquals(standing, optionalStanding.get());
        Assertions.assertThat(optionalStanding).isPresent();
    }

    @Test
    void update() {
        Standing oldStanding = Standing.builder().build();
        final Integer WINS = 4;
        final Integer DRAWS = 5;
        final Integer LOSSES = 6;
        Standing newStanding = Standing.builder().wins(WINS).draws(DRAWS).losses(LOSSES).build();

        when(standingRepository.saveAndFlush(any(Standing.class))).thenReturn(oldStanding);

        Standing standingUpdated = standingService.update(oldStanding, newStanding);
        assertNotNull(standingUpdated);
        assertEquals(WINS, standingUpdated.getWins());
        assertEquals(DRAWS, standingUpdated.getDraws());
        assertEquals(LOSSES, standingUpdated.getLosses());
    }

    @Test
    void delete() {
        standingService.delete(UUID.randomUUID());
        verify(standingRepository, times(1)).deleteById(any(UUID.class));
    }
}
