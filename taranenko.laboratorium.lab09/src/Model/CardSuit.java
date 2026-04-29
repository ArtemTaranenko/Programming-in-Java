package Model;

public enum CardSuit
{
    Heart("♥", 1),
    Spade("♠", 2),
    Club("♣", 3),
    Diamond("♦", 4);


    private final String symbol;
    private final int value;

    CardSuit(String symbol, int value)
    {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol()
    {
        return this.symbol;
    }

    public int getValue()
    {
        return this.value;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
