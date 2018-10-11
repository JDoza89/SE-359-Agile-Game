package edu.depaul.se359.agilegame;

public class ProjectManagerPlayer extends Player {

    private int id = Player.id++;
    private PlayerType type = PlayerType.PROJECT_MANAGER;

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
