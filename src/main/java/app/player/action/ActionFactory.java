package app.player.action;

import app.player.Player;
import org.springframework.stereotype.Component;

@Component
public class ActionFactory {
    public Grab createGrabAction(Player player) {
        return new Grab(player);
    }

    public Drop createDropAction(Grab grab) {
        return new Drop(grab);
    }
}
