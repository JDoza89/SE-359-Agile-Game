package edu.depaul.se359.agilegame;

public class DeveloperPlayer extends Player {

    private int id = Player.id++;
    private PlayerType type = PlayerType.DEVELOPER;

    @Override
    public void doAction() {

    }

    @Override
    public int getId() {
        return this.id;
    }

    public PlayerType getPlayerType() {

        return this.type;

    }

}
