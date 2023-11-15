package de.games.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TicTacToeService {
    private final TicTacToeRepository repository;

    public List<Character> getPosition() {
        return repository.getPositions();
    }

    public String saveMove(int move) {
        List<Character> positions = repository.getPositions();
        positions.set(move, repository.getPlayer());
        repository.setPositions(positions);

        return "move was saved";
    }
}
