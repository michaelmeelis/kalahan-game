package app.board;

import app.pit.exception.KalahaNotFoundException;
import app.pit.model.Kalaha;
import app.pit.model.Pit;
import app.player.Player;
import com.google.common.collect.Lists;

import java.util.*;

public class Board {
    private HashMap<Integer, Pit> pits;

    public HashMap<Integer, Pit> getPits() {
        return pits;
    }

    public void setPits(HashMap<Integer, Pit> pits) {
        this.pits = pits;
    }

    public Integer getActiveStones(Player player) {
        HashMap<Integer, Pit> playerPits = getPlayerPits(player);

        return countActiveStones(playerPits);
    }

    public Integer getAllActiveStones(){
        return countActiveStones(this.pits);
    }

    public ArrayList<Pit> getReversePlayerPits(Player player) {
        HashMap<Integer, Pit> playerPits = getPlayerPits(player);

        Collection<Pit> pits = playerPits.values();
        List<Pit> listPits = Arrays.asList(pits.toArray(new Pit[pits.size()]));
        return new ArrayList<>(Lists.reverse(listPits));

    }

    public HashMap<Integer, Pit> getPlayerPits(Player player) {
        HashMap<Integer, Pit> playerPits = new HashMap<>();
        for (Map.Entry<Integer, Pit> entry : this.pits.entrySet()) {
            Pit pit = entry.getValue();
            Integer index = entry.getKey();

            if (pit.getOwner().equals(player)) {
                playerPits.put(index, pit);
            }
        }

        return playerPits;
    }

    public Kalaha getKalaha(Player player) throws KalahaNotFoundException {
        for (Map.Entry<Integer, Pit> entry : this.pits.entrySet()) {
            Pit pit = entry.getValue();

            if (pit.getOwner().equals(player) && pit instanceof Kalaha) {
                return (Kalaha) pit;
            }
        }

        throw new KalahaNotFoundException("Kalaha was not found");
    }

    private Integer countActiveStones(HashMap<Integer, Pit> pits) {
        Integer amountStones = 0;
        for (Map.Entry<Integer, Pit> entry : this.pits.entrySet()) {
            Pit pit = entry.getValue();

            if (pit instanceof Kalaha) {
                continue;
            }

            amountStones += pit.getStones().size();
        }

        return amountStones;
    }
}
