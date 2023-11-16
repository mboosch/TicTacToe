package de.games.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ComputerPlayer {
    private final TicTacToeRepository repository;
    private final MoveStrategies strategies;

    public void makeMove() {
        List<Character> positions = repository.getPositions();

        if (repository.getPlayersOrFreePositions(' ').size() != 0) {
            int move = strategies.generateRandomMove();
            positions.set(move, repository.getComputerPlayer());
            repository.setPositions(positions);
            repository.setGameRound(repository.getGameRound() + 1);
        }
    }
}

