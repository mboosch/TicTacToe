package de.games.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GameTest {
    private final TicTacToeRepository repository = Mockito.mock(TicTacToeRepository.class);
    private final VictoryConditions victoryConditions = Mockito.mock(VictoryConditions.class);
    private final Game game = new Game(repository, victoryConditions);
    List<List<Integer>> testVictoryConditions = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(0, 1, 2)),
            new ArrayList<>(Arrays.asList(3, 4, 5)),
            new ArrayList<>(Arrays.asList(6, 7, 8)),
            new ArrayList<>(Arrays.asList(0, 3, 6)),
            new ArrayList<>(Arrays.asList(1, 4, 7)),
            new ArrayList<>(Arrays.asList(2, 5, 8)),
            new ArrayList<>(Arrays.asList(0, 4, 8)),
            new ArrayList<>(Arrays.asList(2, 4, 6))));


    @Test
    void setupGameSetsRoundPositionAndPlayersCorrectly() {
        game.setupGame();
        verify(repository).setGameRound(0);
        verify(repository).setPositions(new ArrayList<>(Arrays.asList(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')));
        verify(repository).setPlayer('X');
        verify(repository).setComputerPlayer('O');
    }

    @Test
    void checkGameStatusCorrectlyReturnsPlayersVictory() {
        when(repository.getPlayer()).thenReturn('X');
        when(victoryConditions.getVictoryConditions()).thenReturn(testVictoryConditions);
        when(victoryConditions.checkForVictory('X', testVictoryConditions)).thenReturn(true);
        String expected = "playersVictory";
        String actual = game.checkGameStatus();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkGameStatusCorrectlyReturnsComputersVictory() {
        when(repository.getPlayer()).thenReturn('X');
        when(repository.getComputerPlayer()).thenReturn('O');
        when(victoryConditions.getVictoryConditions()).thenReturn(testVictoryConditions);
        when(victoryConditions.checkForVictory('X', testVictoryConditions)).thenReturn(false);
        when(victoryConditions.checkForVictory('O', testVictoryConditions)).thenReturn(true);
        String expected = "computersVictory";
        String actual = game.checkGameStatus();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkGameStatusCorrectlyReturnsDraw() {
        when(victoryConditions.getVictoryConditions()).thenReturn(testVictoryConditions);
        when(repository.getPlayer()).thenReturn('X');
        when(repository.getComputerPlayer()).thenReturn('O');
        when(victoryConditions.checkForVictory('X', testVictoryConditions)).thenReturn(false);
        when(victoryConditions.checkForVictory('O', testVictoryConditions)).thenReturn(false);
        when(repository.getPlayersOrFreePositions(' ')).thenReturn(new ArrayList<>(List.of()));
        String expected = "draw";
        String actual = game.checkGameStatus();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkGameStatusCorrectlyReturnsOngoing() {
        when(victoryConditions.getVictoryConditions()).thenReturn(testVictoryConditions);
        when(repository.getPlayer()).thenReturn('X');
        when(repository.getComputerPlayer()).thenReturn('O');
        when(victoryConditions.checkForVictory('X', testVictoryConditions)).thenReturn(false);
        when(victoryConditions.checkForVictory('O', testVictoryConditions)).thenReturn(false);
        when(repository.getPlayersOrFreePositions(' ')).thenReturn(new ArrayList<>(Arrays.asList(0, 1)));
        String expected = "ongoing";
        String actual = game.checkGameStatus();
        Assertions.assertEquals(expected, actual);
    }
}