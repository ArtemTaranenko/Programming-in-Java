package Model;

import java.util.TreeSet;

public class Player
{
    private String Name;
    private TreeSet<Card> Cards;


    public Player(String Name)
    {
        this.Name = Name;
        this.Cards = new TreeSet<>();
    }

    public void addCard(Card card)
    {
        Cards.add(card);
    }

    public void printCards()
    {
        System.out.println(Name + "cards:");
        for (Card card: Cards)
        {
            System.out.print(card + " ");
        }
        System.out.println();
    }

    public void calculateHand()
    {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public TreeSet<Card> getCards() {
        return Cards;
    }

    public void setCards(TreeSet<Card> cards) {
        Cards = cards;
    }
}
