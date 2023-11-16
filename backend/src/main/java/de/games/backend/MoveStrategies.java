package de.games.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class MoveStrategies {
    private final TicTacToeRepository repository;

    public int generateRandomMove() {
        List<Integer> freePositions = repository.getPlayersOrFreePositions(' ');
        return freePositions.get(new Random().nextInt(freePositions.size()));
    }
}
