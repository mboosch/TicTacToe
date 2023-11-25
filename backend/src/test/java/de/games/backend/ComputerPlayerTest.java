package de.games.backend;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ComputerPlayerTest {
    private final TicTacToeRepository repository = Mockito.mock(TicTacToeRepository.class);
    private final MoveStrategies strategies = Mockito.mock(MoveStrategies.class);
    private final ComputerPlayer computerPlayer = new ComputerPlayer(repository, strategies);

    @Test
    void makeMoveChecksForImmediateWinNoCallOfThreatOrDangerousPositionOrRandomMove(){
        List<Character> positionWithThreatOfImmediateWinLoss = new ArrayList<>(Arrays.asList(
                ' ', 'X', 'O',
                'X', 'O', ' ',
                ' ', ' ', 'X'
        ));
        when(repository.getPositions()).thenReturn(positionWithThreatOfImmediateWinLoss);
        when(repository.getPlayersOrFreePositions(' ')).thenReturn(new ArrayList<>(Arrays.asList(0, 5, 6, 7)));
        when(repository.getComputerPlayer()).thenReturn('O');
        when(strategies.checkForImmediateWinOrLoss('O')).thenReturn(6);
        when(repository.getGameRound()).thenReturn(2);
        computerPlayer.makeMove();
        verify(repository, times(1)).getPositions();
        verify(repository, times(1)).getPlayersOrFreePositions(' ');
        verify(strategies, times(1)).checkForImmediateWinOrLoss('O');
        verify(strategies, times(0)).checkForDangerousPosition();
        verify(strategies, times(0)).generateRandomMove();
        verify(repository, times(1)).setPositions(new ArrayList<>(Arrays.asList(
                ' ', 'X', 'O',
                'X', 'O', ' ',
                'O', ' ', 'X'
        )));
        verify(repository, times(1)).setGameRound(3);
    }

    @Test
    void makeMoveChecksForImmediateLossNoCallOfDangerousPositionOrRandomMove(){
        List<Character> positionWithThreatOfImmediateWinLoss = new ArrayList<>(Arrays.asList(
                ' ', 'O', 'X',
                ' ', ' ', 'X',
                ' ', ' ', ' '
        ));
        when(repository.getPositions()).thenReturn(positionWithThreatOfImmediateWinLoss);
        when(repository.getPlayersOrFreePositions(' ')).thenReturn(new ArrayList<>(Arrays.asList(0, 3, 4, 6, 7, 8)));
        when(repository.getComputerPlayer()).thenReturn('O');
        when(strategies.checkForImmediateWinOrLoss(anyChar())).thenReturn(-1).thenReturn(8);
        when(repository.getGameRound()).thenReturn(1);
        computerPlayer.makeMove();
        verify(repository, times(1)).getPositions();
        verify(repository, times(1)).getPlayersOrFreePositions(' ');
        verify(strategies, times(2)).checkForImmediateWinOrLoss(anyChar());
        verify(strategies, times(0)).checkForDangerousPosition();
        verify(strategies, times(0)).generateRandomMove();
        verify(repository, times(1)).setPositions(new ArrayList<>(Arrays.asList(
                ' ', 'O', 'X',
                ' ', ' ', 'X',
                ' ', ' ', 'O'
        )));
        verify(repository, times(1)).setGameRound(2);
    }

    @Test
    void makeMoveChecksForDangerousPositionsIfNoImmediateWinOrThreatWasFound(){
        List<Character> dangerousPositionNoImmediateWinOrLoss = new ArrayList<>(Arrays.asList(
                ' ', ' ', ' ',
                ' ', 'X', ' ',
                ' ', ' ', ' '
        ));
        when(repository.getPositions()).thenReturn(dangerousPositionNoImmediateWinOrLoss);
        when(repository.getPlayersOrFreePositions(' ')).thenReturn(new ArrayList<>(Arrays.asList(0, 1, 2, 3, 5, 6, 7,8)));
        when(repository.getComputerPlayer()).thenReturn('O');
        when(strategies.checkForImmediateWinOrLoss(anyChar())).thenReturn(-1);
        when(strategies.checkForDangerousPosition()).thenReturn(6);
        when(repository.getGameRound()).thenReturn(0);
        computerPlayer.makeMove();
        verify(repository, times(1)).getPositions();
        verify(repository, times(1)).getPlayersOrFreePositions(' ');
        verify(strategies, times(2)).checkForImmediateWinOrLoss(anyChar());
        verify(strategies, times(1)).checkForDangerousPosition();
        verify(strategies, times(0)).generateRandomMove();
        verify(repository, times(1)).setPositions(new ArrayList<>(Arrays.asList(
                ' ', ' ', ' ',
                ' ', 'X', ' ',
                'O', ' ', ' '
        )));
        verify(repository, times(1)).setGameRound(1);
    }

    @Test
    void makeMoveCallsGetRandomMoveIfNoOtherMoveWasMade(){
        List<Character> positionNoDangerNoImmediateWinOrLoss = new ArrayList<>(Arrays.asList(
                'X', 'X', 'O',
                'O', 'O', 'X',
                'X', ' ', ' '
        ));
        when(repository.getPositions()).thenReturn(positionNoDangerNoImmediateWinOrLoss);
        when(repository.getPlayersOrFreePositions(' ')).thenReturn(new ArrayList<>(Arrays.asList(7,8)));
        when(strategies.checkForImmediateWinOrLoss(anyChar())).thenReturn(-1);
        when(strategies.checkForDangerousPosition()).thenReturn(-1);
        when(repository.getGameRound()).thenReturn(3);
        computerPlayer.makeMove();
        verify(repository, times(1)).getPositions();
        verify(repository, times(1)).getPlayersOrFreePositions(' ');
        verify(strategies, times(2)).checkForImmediateWinOrLoss(anyChar());
        verify(strategies, times(1)).checkForDangerousPosition();
        verify(strategies, times(1)).generateRandomMove();
        verify(repository, times(1)).setPositions(anyList());
        verify(repository, times(1)).setGameRound(4);
    }

    @Test
    void makeMoveDoesNothingIfNoEmptyFieldIsLeft() {
        List<Integer> emptyList = new ArrayList<>(List.of());
        List<Character> positionWithNoEmptyField = new ArrayList<>(Arrays.asList(
                'X', 'X', 'O',
                'O', 'O', 'X',
                'X', 'O', 'X'
        ));
        when(repository.getPlayersOrFreePositions(' ')).thenReturn(emptyList);
        when(repository.getPositions()).thenReturn(positionWithNoEmptyField);
        computerPlayer.makeMove();
        when(repository.getGameRound()).thenReturn(4);
        verify(repository, times(1)).getPositions();
        verify(repository, times(1)).getPlayersOrFreePositions(' ');
        verify(strategies, times(0)).checkForImmediateWinOrLoss(anyChar());
        verify(strategies, times(0)).checkForDangerousPosition();
        verify(strategies, times(0)).generateRandomMove();
        verify(repository, times(0)).setPositions(anyList());
        verify(repository, times(0)).setGameRound(5);
    }
}