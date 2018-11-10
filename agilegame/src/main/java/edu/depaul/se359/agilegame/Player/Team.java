package edu.depaul.se359.agilegame.Player;

/*
    The team class represents a team and owns an array list of player objects, which can be any player type because of
    all player classes inherited the Player abstract class.

    Author:     Ethan Lin
    Created on: October 11, 2018
 */

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Card.StoryCard;

import java.util.ArrayList;

public class Team {

    private static int teamCount = 1;
    private final int id;
    private int storyPoint;

    private final ArrayList<Player> players;
    private final ArrayList<StoryCard> stories;

    public Team() {

        this.id = Team.teamCount++;
        this.storyPoint = 0;
        this.players = new ArrayList<>();
        this.stories = new ArrayList<>();
    }

    public int getId() {

        return this.id;

    }

    public int setStoryPoint(String sign, int val)
    {
        if (sign.equals("-"))
        {
            this.storyPoint -= val;
        }
        else
        {
            this.storyPoint += val;
        }
        return this.storyPoint;
    }

    public int getStoryPoint()
    {
        return this.storyPoint;
    }

    public void setStory(StoryCard story){
        this.stories.add(story);
    }

    public String getStoryCards(){
        StringBuilder sb = new StringBuilder();
        ArrayList<StoryCard> s = this.stories;

        for (int i = 0; i < stories.size(); i++){
            sb.append(i+1);
            sb.append(". ");
            sb.append(s.get(i).getContent() + " - amount: " + s.get(i).getAmount());
            sb.append("\n");
        }
        return sb.toString();
    }

    public void addPlayer(Player player) {

        this.players.add(player);

    }

    public Player getPlayer(int playerId ) {

        Player playerToGet = null;

        for (Player player : this.players) {

            if (player.getId() == playerId) {

                playerToGet = player;
                break;

            }

        }

        return playerToGet;

    }

    public ArrayList<Player> getAllPlayers() {

        return this.players;

    }

    public int getPlayerCount() {

        return this.players.size();

    }

}
