package app.game;

import app.pit.model.Pit;
import app.player.Player;
import app.player.PlayerNotFoundException;
import app.player.PlayerService;
import app.player.action.Drop;
import app.player.action.Grab;
import app.player.resolve.ResolveInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@EnableAutoConfiguration
public class GameController {

    private GameService gameService;
    private PlayerService playerService;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService) {
        this.gameService = gameService;
        this.playerService = playerService;
    }

    @GetMapping("/")
    public String serveIndexPage() {
        return "index";
    }

    @GetMapping("/game")
    public String showNewGamePage() {
        return "game_new";
    }

    @PostMapping("/game")
    public String handleNewGame(
            @RequestParam(value = "player1", defaultValue = "player1") String player1,
            @RequestParam(value = "player2", defaultValue = "player2") String player2

    ) {
        Game game = this.gameService.createNewGame(player1, player2);

        this.gameService.saveGame(game);

        return "redirect:game/" + game.getGameUuid();
    }

    @GetMapping("/game/{game}")
    public String showGamePage(
            ModelMap model,
            @PathVariable("game") String gameUuid
    ) {
        Game game = this.gameService.loadGame(gameUuid);
        model.addAttribute("game", game);
        model.addAttribute("player1_pits", game.getBoard().getReversePlayerPits(game.getPlayers().get(0)));
        model.addAttribute("player2_pits", game.getBoard().getPlayerPits(game.getPlayers().get(1)));

        return "game";
    }

    @GetMapping("/game/{game}/finished")
    public String showEndGamePage(
            ModelMap model,
            @PathVariable("game") String gameUuid
    ) {
        Game game = this.gameService.loadGame(gameUuid);
        model.addAttribute("game", game);

        return "game_end";
    }

    @PostMapping("/game/{game}")
    public String handlePlayerAction(
            @PathVariable("game") String gameUuid,
            @RequestParam(value = "chosen_pit") Integer chosenPit
    ) {

        Game game = this.gameService.loadGame(gameUuid);

        try {
            Player player = game.getActivePlayer();
            Grab grab = this.playerService.buildGrab(player);
            Drop drop = this.playerService.handleGrabAction(game, grab, chosenPit);

            ResolveInterface resolve = this.playerService.buildResolve(player, drop.getLastPit());
            resolve.handle(player, game.getBoard(), drop.getLastPit());
            this.playerService.endPlayerTurn(game, player);

            if (this.gameService.isFinished(game)) {
                return "redirect:game/" + game.getGameUuid().toString() + "/finished";
            }
        } catch (PlayerNotFoundException notFoundException) {
            return "redirect:game";
        }

        return "redirect:game/" + gameUuid;
    }

}
