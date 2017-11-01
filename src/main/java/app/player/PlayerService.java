package app.player;

import app.board.Board;
import app.game.Game;
import app.pit.model.Pit;
import app.player.action.ActionFactory;
import app.player.action.ActionInterface;
import app.player.action.Drop;
import app.player.action.Grab;
import app.player.resolve.ResolveFactory;
import app.player.resolve.ResolveInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class PlayerService {
    private PlayerFactory playerFactory;
    private ActionFactory actionFactory;
    private ResolveFactory resolveFactory;

    @Autowired
    public PlayerService(PlayerFactory playerFactory, ActionFactory actionFactory, ResolveFactory resolveFactory) {
        this.playerFactory = playerFactory;
        this.actionFactory = actionFactory;
        this.resolveFactory = resolveFactory;
    }

    public ArrayList<Player> buildPlayers(String playerName, String secondPlayerName) {
        return this.playerFactory.createPlayers(playerName, secondPlayerName);
    }

    public Grab buildGrab(Player player) {
        Grab grab = this.actionFactory.createGrabAction(player);
        return grab;
    }

    public Drop buildDropAction(Grab grab) {
        return this.actionFactory.createDropAction(grab);
    }

    public ResolveInterface buildResolve(Player player, Pit pit) {
        return this.resolveFactory.buildResolve(player, pit);
    }

    public Drop handleGrabAction(Game game, Grab grab, Integer chosenPit) {
        this.handlePlayerAction(game, grab, chosenPit);
        return this.dropStones(grab, game.getBoard(), chosenPit);
    }


    private void handlePlayerAction(Game game, ActionInterface action, Integer chosenPit) {
        Pit pit = buildChosenPit(game.getBoard(), chosenPit);
        action.play(pit);
        action.getPlayer().useAction();

    }

    private Pit buildChosenPit(Board board, Integer chosenPit) {
        HashMap<Integer, Pit> pits = board.getPits();
        return pits.get(chosenPit);
    }

    private Drop dropStones(Grab grab, Board board, Integer chosenPit) {
        HashMap<Integer, Pit> pits = board.getPits();
        Drop drop = buildDropAction(grab);
        while (!drop.isEmpty()) {
            Pit nextPit = buildNextPit(pits, chosenPit);
            drop.play(nextPit);
            chosenPit++;
        }

        return drop;
    }

    private Pit buildNextPit(HashMap<Integer, Pit> pits, Integer previousPit) {
        return pits.get(previousPit + 1);
    }

    public void endPlayerTurn(Game game, Player player) throws PlayerNotFoundException {
        if (player.hasAction()) {
            game.setActivePlayer(player);

            return;
        }

        game.setActivePlayer(buildNextPlayer(game, player));
    }

    private Player buildNextPlayer(Game game, Player player) throws PlayerNotFoundException {
        ArrayList<Player> players = game.getPlayers();
        for (Player loopPlayer : players) {
            if (!loopPlayer.equals(player)) {
                return player;
            }
        }

        throw new PlayerNotFoundException("Not enough players found?");
    }
}
