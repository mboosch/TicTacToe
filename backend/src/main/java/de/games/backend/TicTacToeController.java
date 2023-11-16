package de.games.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tictactoe/")
public class TicTacToeController {
    private final TicTacToeService service;
    private final Game game;

    @GetMapping
    public List<Character> getPosition() {
        return service.getPosition();
    }

    @GetMapping("status")
    public String checkGameStatus() {
        return game.checkGameStatus();
    }

    @PostMapping("{move}")
    public String saveMove(@PathVariable int move) {
        return service.saveMove(move);
    }
}