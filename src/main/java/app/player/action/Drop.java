package app.player.action;

import app.pit.model.Kalaha;
import app.pit.model.Pit;
import app.pit.model.Stone;
import app.player.Player;

import java.util.ArrayList;

public class Drop implements ActionInterface {
    private ArrayList<Stone> stones;
    private Player player;
    private Pit lastPit;

    public Drop(Grab grab) {
        this.stones = grab.getStones();
        this.player = grab.getPlayer();
    }

    public Boolean isEmpty() {
        return this.stones.isEmpty();
    }

    public Pit getLastPit() {
        if (null == this.lastPit) {
            return null;
        }

        return this.lastPit;
    }

    public ArrayList<Stone> getStones() {
        return this.stones;
    }

    public Integer getAmountStones() {
        return this.stones.size();
    }

    public Player getPlayer() {
        return player;
    }

    public void play(Pit pit) {
        if (!this.validatePit(pit)) {
            return;
        }
        Stone stone = addStoneToPit(pit);

        removeStone(pit, stone);
    }

    private void removeStone(Pit pit, Stone stone) {
        this.stones.remove(stone);
        this.stones.trimToSize();

        if (this.stones.isEmpty()) {
            this.lastPit = pit;
        }
    }

    private boolean validatePit(Pit pit) {
        return !(pit instanceof Kalaha) || pit.getOwner().equals(this.player);

    }

    private Stone addStoneToPit(Pit pit) {
        int lastStoneIndex = this.stones.size() - 1;
        Stone stone = this.stones.get(lastStoneIndex);
        pit.addStone(stone);

        return stone;
    }
}
