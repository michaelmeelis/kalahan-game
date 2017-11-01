package app.player.resolve;

import app.pit.model.Kalaha;
import app.pit.model.Pit;
import app.player.Player;
import org.springframework.stereotype.Component;

@Component
public class ResolveFactory {
    public ResolveInterface buildResolve(Player player, Pit pit) {
        if (pit instanceof Kalaha) {
            return new ExtraTurn();
        }

        if (pit.getStones().size() == 0 && pit.getOwner().equals(player)) {
            return new Capture();
        }

        return new Nothing();
    }
}
