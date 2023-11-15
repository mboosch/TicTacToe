package de.games.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class Game {
    private final TicTacToeRepository repository;

    public void setupGame() {
        repository.setGameRound(0);
        List<Character> position = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            position.add(' ');
        }
        repository.setPositions(position);
        repository.setPlayer('X');
        repository.setComputerPlayer('O');
    }
}


