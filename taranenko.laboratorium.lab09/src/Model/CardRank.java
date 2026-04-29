package Model;

public enum CardRank
{
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    private final String rank;
    private final int value;

    CardRank(String rank, int value)
    {
        this.rank = rank;
        this.value = value;
    }

    public String getRank()
    {
        return this.rank;
    }

    public int getValue()
    {
        return this.value;
    }

    @Override
    public String toString() {
        return rank;
    }
}
