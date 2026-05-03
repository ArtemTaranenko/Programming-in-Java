package Model;

import java.util.List;

public class HandValue
{
    private Combination combination;
    private List<Integer> ranks;


    public HandValue(Combination combination, List<Integer> ranks) {
        this.combination = combination;
        this.ranks = ranks;
    }

    public Combination getCombination() {
        return combination;
    }

    public void setCombination(Combination combination) {
        this.combination = combination;
    }

    public List<Integer> getRanks() {
        return ranks;
    }

    public void setRanks(List<Integer> ranks) {
        this.ranks = ranks;
    }
}
