package edu.depaul.se359.agilegame;

class ProjectManagerPlayer extends Player {

    private final int id = Player.id++;
    private final PlayerType type = PlayerType.PROJECT_MANAGER;

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
