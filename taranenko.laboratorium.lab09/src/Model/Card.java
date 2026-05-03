package Model;

public class Card implements Comparable<Card>
{
    private final CardSuit cardSuit;
    private final CardRank cardRank;


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

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public CardRank getCardRank() {
        return cardRank;
    }
}
