package app.player;

public class Player {
    private Integer points;
    private Boolean startingPlayer;
    private String name;
    private Integer amountAction = 0;

    public Player(){
        this.points = 0;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void addPoints(Integer points) {
        this.points += points;
    }

    public Boolean isStartingPlayer() {
        return startingPlayer;
    }

    public void setStartingPlayer(Boolean startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountAction() {
        return amountAction;
    }

    public void useAction() {
        this.amountAction--;
    }

    public void gainAction() {
        this.amountAction++;
    }

    public Boolean hasAction() {
        return this.amountAction > 0;
    }

}
