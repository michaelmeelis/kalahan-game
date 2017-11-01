package app.pit.factory;

import app.pit.model.Kalaha;
import app.pit.model.Pit;
import app.pit.model.Stone;
import app.player.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class PitFactory {
    public static final int[] PIT_OPPOSITION_MAPPING = {12, 11, 10, 9, 8, 7, 0, 5, 4, 3, 2, 1, 0};
    public static final Integer AMOUNT_OF_STONES_PER_PIT = 6;
    public static final Integer AMOUNT_OF_STONES_PER_KALAHA = 0;


    public Pit createPit(Player player, Integer position) {
        Pit pit = new Pit();
        pit.setOwner(player);
        pit.setPosition(position);
        pit.setOpposite(PIT_OPPOSITION_MAPPING[position]);
        pit.setStones(createStones(AMOUNT_OF_STONES_PER_PIT));

        return pit;
    }

    private ArrayList<Stone> createStones(Integer amount) {
        return new ArrayList<Stone>(Collections.nCopies(amount, new Stone()));
    }

    public Pit createKalaha(Player player, Integer currentSize) {
        Kalaha kalaha = new Kalaha();
        kalaha.setOwner(player);
        kalaha.setPosition(currentSize);
        kalaha.setStones(createStones(AMOUNT_OF_STONES_PER_KALAHA));

        return kalaha;
    }
}
