package app.player.resolve;

import app.board.Board;
import app.pit.model.Pit;
import app.player.Player;

public class Nothing implements ResolveInterface {
    public void handle(Player player, Board board, Pit pit) {
    }
}
