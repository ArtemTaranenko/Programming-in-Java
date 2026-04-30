package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck
{
    private final List<Card> deck = new ArrayList<>();
    public Deck()
    {
        for (CardRank rank : CardRank.values()) {
            for(CardSuit suit : CardSuit.values()){
                deck.add(new Card(suit, rank));
            }
        }
    }

    public void shuffling()
    {
        Collections.shuffle(deck);
    }

    public void printDeck()
    {
        for (var item: deck){
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public List<Card> getDeck() {
        return deck;
    }
}
