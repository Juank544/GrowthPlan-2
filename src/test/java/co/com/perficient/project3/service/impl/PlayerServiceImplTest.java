package co.com.perficient.project3.service.impl;

import co.com.perficient.project3.model.entity.Player;
import co.com.perficient.project3.repository.PlayerRepository;
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

class PlayerServiceImplTest {

    @InjectMocks
    private PlayerServiceImpl playerService;
    @Mock
    private PlayerRepository playerRepository;

    final UUID ID_PLAYER = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void create() {
        Player player = Player.builder().id(ID_PLAYER).build();

        when(playerRepository.save(any(Player.class))).thenReturn(player);

        Player playerCreated = playerService.create(player);
        assertNotNull(playerCreated);
        assertEquals(ID_PLAYER, playerCreated.getId());
    }

    @Test
    void findAll() {
        when(playerRepository.findAll()).thenReturn(Collections.singletonList(Player.builder().build()));

        List<Player> players = playerService.findAll();
        Assertions.assertThat(players).isNotNull().isNotEmpty();
    }

    @Test
    void findById() {
        Player player = Player.builder().id(ID_PLAYER).build();

        when(playerRepository.findById(any(UUID.class))).thenReturn(Optional.of(player));

        Optional<Player> optionalPlayer = playerService.findById(ID_PLAYER);
        assertNotNull(optionalPlayer);
        assertEquals(player, optionalPlayer.get());
        Assertions.assertThat(optionalPlayer).isPresent();
    }

    @Test
    void update() {
        Player oldPlayer = Player.builder().build();
        final String NAME = "playerName";
        final String NATIONALITY = "Colombian";
        final String NUMBER = "10";
        final String POSITION = "Striker";
        Player newPlayer = Player.builder().birthDate(LocalDate.now()).name(NAME).nationality(NATIONALITY)
                .number(NUMBER).position(POSITION).build();

        when(playerRepository.saveAndFlush(any(Player.class))).thenReturn(oldPlayer);

        Player playerUpdated = playerService.update(oldPlayer, newPlayer);
        assertNotNull(playerUpdated);
        assertNotNull(playerUpdated.getBirthDate());
        assertEquals(NAME, playerUpdated.getName());
        assertEquals(NATIONALITY, playerUpdated.getNationality());
        assertEquals(NUMBER, playerUpdated.getNumber());
        assertEquals(POSITION, playerUpdated.getPosition());
    }

    @Test
    void delete() {
        playerService.delete(UUID.randomUUID());
        verify(playerRepository, times(1)).deleteById(any(UUID.class));
    }
}
