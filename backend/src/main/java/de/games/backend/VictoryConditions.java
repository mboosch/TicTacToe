package de.games.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VictoryConditions {
    private final TicTacToeRepository repository;

    public final List<List<Integer>> getVictoryConditions() {
        return new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(0, 1, 2)),
                new ArrayList<>(Arrays.asList(3, 4, 5)),
                new ArrayList<>(Arrays.asList(6, 7, 8)),
                new ArrayList<>(Arrays.asList(0, 3, 6)),
                new ArrayList<>(Arrays.asList(1, 4, 7)),
                new ArrayList<>(Arrays.asList(2, 5, 8)),
                new ArrayList<>(Arrays.asList(0, 4, 8)),
                new ArrayList<>(Arrays.asList(2, 4, 6))));
    }

    public boolean checkForVictory(char player, List<List<Integer>> victoryConditions) {
        for (List<Integer> row : victoryConditions) {
            if (new HashSet<>(repository.getPlayersOrFreePositions(player)).containsAll(row)) {
                return true;
            }
        }
        return false;
    }

    public int findMissingElementForTwoMetVictoryConditions(List<Integer> victoryCondition, List<Integer> playersPositions) {
        int matchingCount = 0;
        int missingElement = 0;

        for (int position : victoryCondition) {
            if (playersPositions.contains(position)) {
                matchingCount++;
            } else missingElement = position;
        }

        if (matchingCount == 2) {
            return missingElement;
        } else return -1;
    }
}
