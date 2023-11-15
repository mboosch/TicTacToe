package de.games.backend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class TicTacToeRepository {
    private List<Character> positions;
    private char player;
    private char computerPlayer;
}