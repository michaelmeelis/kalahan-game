package app.game;

import app.board.Board;
import app.player.Player;
import app.player.PlayerNotFoundException;

import java.util.ArrayList;
import java.util.UUID;

public class Game {
    private UUID gameUuid;
    private ArrayList<Player> players;
    private Board board;
    private Player activePlayer;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer(String active_player) throws PlayerNotFoundException {
        for (Player player : this.players) {
            if (player.getName().equals(active_player)) {
                return player;
            }
        }

        throw new PlayerNotFoundException("Player is not found");
    }

    public UUID getGameUuid() {
        return gameUuid;
    }

    public void setGameUuid(UUID gameUuid) {
        this.gameUuid = gameUuid;
    }

    public Boolean isFinished() {
        for (Player player : this.players) {
            if (this.board.getActiveStones(player).equals(0)) {
                return true;
            }
        }

        return false;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }
}
