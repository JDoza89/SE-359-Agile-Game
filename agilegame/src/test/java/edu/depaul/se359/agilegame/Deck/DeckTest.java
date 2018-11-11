package edu.depaul.se359.agilegame.Deck;

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Card.ChanceCard;
import edu.depaul.se359.agilegame.Card.RoleCard;
import edu.depaul.se359.agilegame.Card.StoryCard;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @AfterAll
    static void clearDecks()
    {
        Deck.getInstance().clearDecks();
    }

    @Test
    //Tests add method of Deck class to ensure that a card of type chance card gets added to the chance card deck list.
    void addChanceCard() {

        String type = "Chance";
        String id = "CP001";
        String role = "PO";
        String content = "Your Client wants to add a new feature";
        String description = "None";
        String effect = "pointChange";
        String amount = "+10";
        Deck.AddCard(type,id,role,content,description,effect,amount);
        ArrayList<ChanceCard> cards = Deck.getChanceDeck();
        Card card = cards.get(0);
        assertEquals(card.getId(),id);
        assertEquals(card.getRole(),role);
        assertEquals(card.getContent(),content);
        assertEquals(card.getDescription(),description);
        assertEquals(card.getEffect(),effect);
        assertEquals(card.getAmount(),amount);
    }

    @Test
    //Tests add method of Deck class to ensure that a card of type role card gets added to the role card deck list.
    void addRoleCard() {
        String type = "Role";
        String id = "RD000";
        String role = "DEV";
        String content = "You stay after work all week.";
        String description = "You finished your stories early";
        String effect = "pointChange";
        String amount = "-10";
        Deck.AddCard(type,id,role,content,description,effect,amount);
        ArrayList<RoleCard> cards = Deck.getRoleDeck();
        Card card = cards.get(0);
        assertEquals(card.getId(),id);
        assertEquals(card.getRole(),role);
        assertEquals(card.getContent(),content);
        assertEquals(card.getDescription(),description);
        assertEquals(card.getEffect(),effect);
        assertEquals(card.getAmount(),amount);
    }

    @Test
    //Tests add method of Deck class to ensure that a card of type story card gets added to the story card deck list.
    void addStoryCard() {
        String type = "Story";
        String id = "S000";
        String role = "None";
        String content = "Cure Cancer!";
        String description = "Simple, really. Just cure cancer, how hard can it be?";
        String effect = "addStory";
        String amount = "+30";
        Deck.AddCard(type,id,role,content,description,effect,amount);
        ArrayList<StoryCard> cards = Deck.getStoryDeck();
        Card card = cards.get(0);
        assertEquals(card.getId(),id);
        assertEquals(card.getRole(),role);
        assertEquals(card.getContent(),content);
        assertEquals(card.getDescription(),description);
        assertEquals(card.getEffect(),effect);
        assertEquals(card.getAmount(),amount);
    }
}