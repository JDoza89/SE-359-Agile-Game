package edu.depaul.se359.agilegame;

public class ScrumMasterPlayer extends Player {

    private int id = Player.id++;
    private PlayerType type = PlayerType.SCRUM_MASTER;

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
