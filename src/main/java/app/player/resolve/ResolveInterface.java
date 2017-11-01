package app.player.resolve;

import app.board.Board;
import app.pit.model.Pit;
import app.player.Player;

public interface ResolveInterface {
    void handle(Player player, Board board, Pit pit);
}
