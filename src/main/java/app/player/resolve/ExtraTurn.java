package app.player.resolve;

import app.board.Board;
import app.pit.model.Pit;
import app.player.Player;

public class ExtraTurn implements ResolveInterface {
    public void handle(Player player, Board board, Pit pit) {
        player.gainAction();
    }
}
