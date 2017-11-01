package app.pit.model;

import app.player.Player;

import java.util.ArrayList;

public class Pit {
    protected Player owner;
    protected Integer position;
    protected ArrayList<Stone> stones;

    private Integer opposite;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getOpposite() {
        return opposite;
    }

    public void setOpposite(Integer opposite) {
        this.opposite = opposite;
    }

    public ArrayList<Stone> getStones() {
        return stones;
    }

    public void setStones(ArrayList<Stone> stones) {
        this.stones = stones;
    }

    public void addStone(Stone stone) {
        this.stones.add(stone);
    }

    public void addStones(ArrayList<Stone> stones) {
        this.stones.addAll(stones);
    }

    public void removeStones() {
        this.stones.clear();
    }
}
