package app.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {
    private GameFactory gameFactory;
    private GameRepository gameRepository;

    @Autowired
    public GameService(GameFactory gameFactory, GameRepository gameRepository) {
        this.gameFactory = gameFactory;
        this.gameRepository = gameRepository;
    }

    public Game createNewGame(String playerName, String secondPlayerName) {
        return this.gameFactory.createNew(playerName, secondPlayerName);
    }

    public Game loadGame(String uuid) {
        UUID gameUuid = UUID.fromString(uuid);
        return this.gameRepository.findByUuid(gameUuid);
    }

    public void saveGame(Game game) {
        this.gameRepository.persist(game);
    }

    public Boolean isFinished(Game game) {
        return game.isFinished();
    }
}
