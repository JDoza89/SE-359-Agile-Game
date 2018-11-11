package edu.depaul.se359.agilegame.GameState;

public class Phase {
    private int phasePosition;
    private String phaseName;
    private String phaseDescription;

    public Phase(int phasePosition, String phaseName, String phaseDescription){
        this.phasePosition = phasePosition;
        this.phaseName = phaseName;
        this.phaseDescription = phaseDescription;
    }

    public int getPhasePosition(){
        return phasePosition;
    }

    public String getPhaseName(){
        return  phaseName;
    }

    public String getPhaseDescription(){
        return phaseDescription + "\n";
    }

    @Override
    public String toString(){
        return phasePosition + ". " + phaseName + ": " + phaseDescription;
    }
}
