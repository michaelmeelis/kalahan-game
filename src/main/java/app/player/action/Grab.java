package app.player.action;

import app.pit.model.Pit;
import app.pit.model.Stone;
import app.player.Player;

import java.util.ArrayList;

public class Grab implements ActionInterface{
    private ArrayList<Stone> stones;
    private Player player;
    private Pit grabbedPit;

    public Grab(Player player) {
        this.player = player;
    }

    public void play(Pit pit) {
        this.grabbedPit = pit;
        this.stones = (ArrayList<Stone>) pit.getStones().clone();

        pit.removeStones();
    }

    public Player getPlayer() {
        return this.player;
    }

    public ArrayList<Stone> getStones() {
        return stones;
    }
}
