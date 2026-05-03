package Model;

public class WinSituation
{
    private Win win;
    private Combination combination;

    public WinSituation(Win win, Combination combination)
    {
        this.win = win;
        this.combination = combination;
    }

    public WinSituation(Win win) {
        this.win = win;
    }

    public Win getWin()
    {
        return this.win;
    }

    public Combination getCombination()
    {
        return this.combination;
    }

    public void setWin(Win win) {
        this.win = win;
    }

    public void setCombination(Combination combination) {
        this.combination = combination;
    }
}
