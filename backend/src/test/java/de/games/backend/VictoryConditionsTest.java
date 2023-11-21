package de.games.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

class VictoryConditionsTest {
    private final TicTacToeRepository repository = Mockito.mock(TicTacToeRepository.class);
    private final VictoryConditions victoryConditions = new VictoryConditions(repository);
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
    void getVictoryConditionsCorrectlyReturnsVictoryConditions() {
        List<List<Integer>> expected = testVictoryConditions;
        List<List<Integer>> actual = victoryConditions.getVictoryConditions();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkForVictoryCorrectlyReturnsTrue() {
        when(repository.getPlayersOrFreePositions('X')).thenReturn(new ArrayList<>(Arrays.asList(1, 4, 7)));
        boolean expected = true;
        boolean actual = victoryConditions.checkForVictory('X', testVictoryConditions);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkForVictoryCorrectlyReturnsFalse() {
        when(repository.getPlayersOrFreePositions('X')).thenReturn(new ArrayList<>(Arrays.asList(1, 4, 8)));
        boolean expected = false;
        boolean actual = victoryConditions.checkForVictory('X', testVictoryConditions);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkForVictoryCorrectlyReturnsFalseIfGivenEmptyList() {
        when(repository.getPlayersOrFreePositions('O')).thenReturn(new ArrayList<>(List.of()));
        boolean expected = false;
        boolean actual = victoryConditions.checkForVictory('O', testVictoryConditions);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findMissingElementCorrectlyReturnsMinusOne() {
        List<Integer> testVictoryCondition = new ArrayList<>(Arrays.asList(6, 7, 8));
        List<Integer> testPlayersPositions = new ArrayList<>(Arrays.asList(0, 6));
        int expected = -1;
        int actual = victoryConditions.findMissingElementForTwoMetVictoryConditions(testVictoryCondition, testPlayersPositions);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findMissingElementCorrectlyFindsMissingElement() {
        List<Integer> testVictoryCondition = new ArrayList<>(Arrays.asList(6, 7, 8));
        List<Integer> testPlayersPositions = new ArrayList<>(Arrays.asList(0, 6, 7));
        int expected = 8;
        int actual = victoryConditions.findMissingElementForTwoMetVictoryConditions(testVictoryCondition, testPlayersPositions);
        Assertions.assertEquals(expected, actual);
    }
}