package de.games.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TicTacToeRepositoryTest {
    private final TicTacToeRepository repository = new TicTacToeRepository();

    @Test
    void getPlayersReturnsCorrectPlayerPositions() {
        repository.setPositions(new ArrayList<>(Arrays.asList(' ', ' ', ' ', 'X', ' ', 'X', ' ', 'X', ' ')));
        List<Integer> expected = new ArrayList<>(Arrays.asList(3, 5, 7));
        List<Integer> actual = repository.getPlayersOrFreePositions('X');
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getPlayersReturnsCorrectComputerPositions() {
        repository.setPositions(new ArrayList<>(Arrays.asList(' ', ' ', ' ', 'O', ' ', 'O', ' ', 'O', ' ')));
        List<Integer> expected = new ArrayList<>(Arrays.asList(3, 5, 7));
        List<Integer> actual = repository.getPlayersOrFreePositions('O');
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getPlayersReturnsCorrectFreePositions() {
        repository.setPositions(new ArrayList<>(Arrays.asList(' ', ' ', ' ', 'O', ' ', 'O', ' ', 'O', ' ')));
        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 2, 4, 6, 8));
        List<Integer> actual = repository.getPlayersOrFreePositions(' ');
        Assertions.assertEquals(expected, actual);
    }
}