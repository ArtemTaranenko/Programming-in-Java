package Model;

public enum Combination {
    ROYALFLUSH(10),
    STRAIGHTFLUSH(9),
    FOUR(8),
    FULLHOUSE(7),
    FLUSH(6),
    STRAIGHT(5),
    THREE(4),
    TWOPAIR(3),
    ONEPAIR(2),
    HIGHCARD(1);


    private final int value;

    Combination(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
