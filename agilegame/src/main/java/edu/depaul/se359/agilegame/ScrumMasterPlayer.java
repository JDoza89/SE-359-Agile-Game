package edu.depaul.se359.agilegame;

class ScrumMasterPlayer extends Player {

    private final int id = Player.id++;
    private final PlayerType type = PlayerType.SCRUM_MASTER;

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
