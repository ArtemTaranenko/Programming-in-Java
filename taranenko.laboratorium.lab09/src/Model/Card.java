package Model;

public class Card implements Comparable<Card>
{
    private CardSuit cardSuit;
    private CardRank cardRank;


    public Card(CardSuit cardSuit, CardRank cardRank)
    {
        this.cardSuit = cardSuit;
        this.cardRank = cardRank;
    }

    @Override
    public int compareTo(Card other)
    {
        int rankCompare = Integer.compare(this.cardRank.getValue(), other.cardRank.getValue());
        if (rankCompare != 0) return rankCompare;
        return Integer.compare(this.cardSuit.getValue(), other.cardSuit.getValue());
    }

    @Override
    public String toString() {
        return cardSuit + " " + cardRank;
    }
}
