package de.games.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class TicTacToeRepository {
    private List<Character> positions;
    private int gameRound;
    private char player;
    private char computerPlayer;

    public List<Integer> getPlayersOrFreePositions(char playerOrSpace) {
        List<Integer> freePositions = new ArrayList<>();
        for (int i=0; i < getPositions().size(); i++) {
            if (getPositions().get(i).equals(playerOrSpace)) {
                freePositions.add(i);
            }
        }
        return freePositions;
    }
}