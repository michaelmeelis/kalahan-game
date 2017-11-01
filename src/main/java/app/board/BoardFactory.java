package app.board;

import app.pit.factory.PitFactory;
import app.pit.model.Pit;
import app.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class BoardFactory {
    public static final Integer AMOUNT_OF_PITS_PER_PLAYER = 6;
    public static final Integer AMOUNT_OF_KALAHA_PER_PLAYER = 1;

    private PitFactory pitFactory;

    @Autowired
    public BoardFactory(PitFactory pitFactory) {
        this.pitFactory = pitFactory;
    }

    public Board create(ArrayList<Player> players) {
        Board board = new Board();
        HashMap<Integer, Pit> combinedPits = createAllPits(players);

        board.setPits(combinedPits);

        return board;
    }

    private HashMap<Integer, Pit> createAllPits(ArrayList<Player> players) {
        HashMap<Integer, Pit> combinedPits = new HashMap<Integer, Pit>();
        for (Player player : players) {
            combinedPits.putAll(createPlayerPits(player));
            combinedPits.putAll(createPlayerKalaha(player, combinedPits.size()));
        }
        return combinedPits;
    }

    private HashMap<Integer, Pit> createPlayerKalaha(Player player, Integer currentSize) {
        Pit kalaha = this.pitFactory.createKalaha(player, currentSize);
        HashMap<Integer, Pit> kalahaMap = new HashMap<Integer, Pit>();
        kalahaMap.put(kalaha.getPosition(), kalaha);

        return kalahaMap;

    }

    private HashMap<Integer, Pit> createPlayerPits(Player player) {
        Integer startingPosition = buildStartingPosition(player);
        HashMap<Integer, Pit> playerPits = new HashMap<Integer, Pit>();
        for (int i = startingPosition; i < startingPosition + AMOUNT_OF_PITS_PER_PLAYER; i++) {
            playerPits.put(i, this.pitFactory.createPit(player, i));
        }

        return playerPits;
    }

    private Integer buildStartingPosition(Player player) {
        if (player.isStartingPlayer()) {
            return 0;
        }

        return AMOUNT_OF_PITS_PER_PLAYER + AMOUNT_OF_KALAHA_PER_PLAYER;
    }

}
