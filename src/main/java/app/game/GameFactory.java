package app.game;

import app.board.Board;
import app.board.BoardFactory;
import app.player.Player;
import app.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class GameFactory {
    private PlayerService playerService;
    private BoardFactory boardFactory;

    @Autowired
    public GameFactory(PlayerService playerService, BoardFactory boardFactory){
        this.playerService = playerService;
        this.boardFactory = boardFactory;
    }

    public Game createNew(String playerName, String secondPlayerName) {
        ArrayList<Player> players = this.playerService.buildPlayers(playerName, secondPlayerName);
        Board board = this.boardFactory.create(players);

        Game game = new Game();
        game.setPlayers(players);
        game.setBoard(board);
        game.setGameUuid(UUID.randomUUID());
        game.setActivePlayer(players.get(0));

        return game;
    }
}
