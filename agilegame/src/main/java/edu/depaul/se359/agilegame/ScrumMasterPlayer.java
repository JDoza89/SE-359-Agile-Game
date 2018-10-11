package edu.depaul.se359.agilegame;

public class ScrumMasterPlayer implements Player {

    private PlayerType type = PlayerType.SCRUMMASTER;

    @Override
    public void doAction() {

    }

    public PlayerType getPlayerType() {

        return this.type;

    }

}
