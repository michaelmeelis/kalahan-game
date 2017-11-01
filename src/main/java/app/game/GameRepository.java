package app.game;

import app.datastore.Datastore;
import app.datastore.DatastoreInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class GameRepository {
    private DatastoreInterface datastore;

    @Autowired
    GameRepository(Datastore datastore) {
        this.datastore = datastore;
    }

    public Game findByUuid(UUID gameUuid) {
        return (Game) this.datastore.findByUuid("game", gameUuid);
    }

    public void persist(Game game) {
        this.datastore.persist(game.getGameUuid(), game);
    }
}
