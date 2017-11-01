package app.player.resolve;

import app.board.Board;
import app.pit.exception.KalahaNotFoundException;
import app.pit.model.Kalaha;
import app.pit.model.Pit;
import app.pit.model.Stone;
import app.player.Player;

import java.util.ArrayList;

public class Capture implements ResolveInterface{
    public void handle(Player player, Board board, Pit pit) {
        try {
            Integer oppositePitIndex = pit.getOpposite();
            Pit oppositePit = board.getPits().get(oppositePitIndex);

            ArrayList<Stone> stones = getCapturedStones(pit, oppositePit);

            addStonesToKalaha(player, board, stones);
            cleanupPits(pit, oppositePit);
        } catch (KalahaNotFoundException notFoundException) {

        } catch (Exception exception) {

        }
    }

    private void addStonesToKalaha(Player player, Board board, ArrayList<Stone> stones) throws KalahaNotFoundException {
        Kalaha kalaha = board.getKalaha(player);
        kalaha.addStones(stones);
    }

    private ArrayList<Stone> getCapturedStones(Pit pit, Pit oppositePit) {
        ArrayList<Stone> stones = oppositePit.getStones();
        stones.addAll(pit.getStones());
        return stones;
    }

    private void cleanupPits(Pit pit, Pit oppositePit) {
        oppositePit.removeStones();
        pit.removeStones();
    }
}
