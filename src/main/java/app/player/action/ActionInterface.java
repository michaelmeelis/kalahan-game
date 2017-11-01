package app.player.action;

import app.pit.model.Pit;
import app.player.Player;

public interface ActionInterface {
    void play(Pit pit);
    Player getPlayer();
}
