package edu.depaul.se359.agilegame;

public class ProjectManagerPlayer implements Player {

    private PlayerType type = PlayerType.PROJECTMANAGER;

    @Override
    public void doAction() {

    }

    public PlayerType getPlayerType() {

        return this.type;

    }

}
