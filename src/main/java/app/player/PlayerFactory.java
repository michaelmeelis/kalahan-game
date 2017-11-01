package app.player;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PlayerFactory {

    public ArrayList<Player> createPlayers(String playerName, String secondPlayerName) {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(createPlayer(playerName, true));
        players.add(createPlayer(secondPlayerName, false));

        return players;
    }

    private Player createPlayer(String playerName, Boolean startingPlayer) {
        Player player = new Player();
        player.setStartingPlayer(startingPlayer);
        player.setName(playerName);

        return player;
    }
}
