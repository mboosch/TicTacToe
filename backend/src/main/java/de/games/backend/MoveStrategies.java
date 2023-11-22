package de.games.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class MoveStrategies {
    private final VictoryConditions victoryConditions;
    private final TicTacToeRepository repository;

    public int checkForImmediateWinOrLoss(char player) {
        List<Integer> possibleMoves = new ArrayList<>();

        for (List<Integer> condition : victoryConditions.getVictoryConditions()) {
            int result = victoryConditions.findMissingElementForTwoMetVictoryConditions(condition, repository.getPlayersOrFreePositions(player));
            if (result != -1) {
                possibleMoves.add(result);
            }
        }

        int result = -1;
        for (int possibleMove : possibleMoves) {
            if (repository.getPlayersOrFreePositions(' ').contains(possibleMove)) {
                result = possibleMove;
            }
        }
        return result;
    }

    public int checkForDangerousPosition() {
        if (repository.getGameRound() == 1 && repository.getPositions().get(4) == repository.getPlayer()) {
            List<Integer> possibleMoves = new ArrayList<>(Arrays.asList(0, 2, 6, 8));
            return possibleMoves.get(new Random().nextInt(possibleMoves.size()));
        }
        return -1;
    }

    public int generateRandomMove() {
        List<Integer> freePositions = repository.getPlayersOrFreePositions(' ');
        return freePositions.get(new Random().nextInt(freePositions.size()));
    }
}
