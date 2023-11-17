package de.games.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class TicTacToeServiceTest {
    private final TicTacToeRepository ticTacToeRepository = Mockito.mock(TicTacToeRepository.class);
    private final ComputerPlayer computerPlayer = Mockito.mock(ComputerPlayer.class);
    private final Game game = Mockito.mock(Game.class);
    private final TicTacToeService ticTacToeService = new TicTacToeService(ticTacToeRepository, computerPlayer, game);
    List<Character> testPositionsList = new ArrayList<>(Arrays.asList(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '));

    @Test
    void getPositionReturnsExpectedList() {
        when(ticTacToeRepository.getPositions()).thenReturn(testPositionsList);
        List<Character> expected = new ArrayList<>(Arrays.asList(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '));
        List<Character> actual = ticTacToeService.getPosition();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void saveMoveCallsSetupGameIfRepoReturnsNull() {
        when(ticTacToeRepository.getPositions()).thenReturn(null).thenReturn(testPositionsList);
        when(game.checkGameStatus()).thenReturn("ongoing");
        ticTacToeService.saveMove(0);
        verify(game, times(1)).setupGame();
    }

    @Test
    void saveMoveDoesNotCallSetupGameIfRepoReturnsNotNull() {
        when(ticTacToeRepository.getPositions()).thenReturn(testPositionsList);
        when(game.checkGameStatus()).thenReturn("ongoing");
        ticTacToeService.saveMove(0);
        verify(game, times(0)).setupGame();
    }

    @Test
    void saveMoveSavesMoveCorrectly() {
        when(ticTacToeRepository.getGameRound()).thenReturn(0);
        when(ticTacToeRepository.getPositions()).thenReturn(testPositionsList);
        when(ticTacToeRepository.getPlayer()).thenReturn('X');
        when(game.checkGameStatus()).thenReturn("ongoing");
        List<Character> expected = new ArrayList<>(Arrays.asList(' ', 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' '));
        ticTacToeService.saveMove(1);
        verify(ticTacToeRepository).setPositions(expected);
    }

    @Test
    void saveMoveSetsRoundPlusOneAndCallsComputerPlayerIfGameIsOngoing() {
        when(ticTacToeRepository.getGameRound()).thenReturn(0);
        when(ticTacToeRepository.getPositions()).thenReturn(testPositionsList);
        when(ticTacToeRepository.getPlayer()).thenReturn('X');
        when(game.checkGameStatus()).thenReturn("ongoing");
        ticTacToeService.saveMove(0);
        verify(ticTacToeRepository).setGameRound(1);
        verify(computerPlayer, times(1)).makeMove();
    }

    @Test
    void saveMoveDoesNotChangeRoundOrTriggerComputerPlayerIfGameIsNotOngoing() {
        when(ticTacToeRepository.getGameRound()).thenReturn(0);
        when(ticTacToeRepository.getPositions()).thenReturn(testPositionsList);
        when(ticTacToeRepository.getPlayer()).thenReturn('X');
        when(game.checkGameStatus()).thenReturn("playersVictory");
        ticTacToeService.saveMove(0);
        verify(ticTacToeRepository, times(0)).setGameRound(1);
        verify(computerPlayer, times(0)).makeMove();
    }

    @Test
    void saveMoveConfirmsThatMovesWasSaved() {
        when(ticTacToeRepository.getGameRound()).thenReturn(0);
        when(ticTacToeRepository.getPositions()).thenReturn(testPositionsList);
        when(ticTacToeRepository.getPlayer()).thenReturn('X');
        when(game.checkGameStatus()).thenReturn("ongoing");
        String expected = "move was saved";
        String actual = ticTacToeService.saveMove(0);
        Assertions.assertEquals(expected, actual);
    }
}