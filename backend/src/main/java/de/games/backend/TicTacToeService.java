package de.games.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TicTacToeService {
    private final TicTacToeRepository repository;
    private final Game game;

    public List<Character> getPosition() {
        return repository.getPositions();
    }

    public String saveMove(int move) {
        if (repository.getPositions() == null) {
            game.setupGame();
        }
        List<Character> positions = repository.getPositions();
        positions.set(move, repository.getPlayer());
        repository.setPositions(positions);

        return "move was saved";
    }
}
