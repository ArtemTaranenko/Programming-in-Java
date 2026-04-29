package Model;

import java.util.TreeSet;

public class Player
{
    private String Name;
    private TreeSet<Card> Cards;


    public Player(String Name, TreeSet<Card> Cards)
    {
        this.Name = Name;
        this.Cards = Cards;
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
