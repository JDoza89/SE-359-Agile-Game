package edu.depaul.se359.agilegame;

public class DeveloperPlayer implements Player {

    private PlayerType type = PlayerType.DEVELOPER;

    @Override
    public void doAction() {

    }

    public PlayerType getPlayerType() {

        return this.type;

    }

}
