package Model;

public class Point
{
    private int score;
    private int num_rounds;


    public Point(int score, int num_rounds) {
        this.score = score;
        this.num_rounds = num_rounds;
    }

    public Point() {
    }

    public void Add_Score(int score)
    {
        this.score += score;
    }

    public void calculate_score(int consolation_points, int distance)
    {
        int gain = Math.max(0, consolation_points - distance);
        score += gain;

    }

    public void print_ending_score()
    {
        System.out.println("\tCongratulations! You`ve guessed searched point in round " + num_rounds + " and scored " + score + " points");
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNum_rounds() {
        return num_rounds;
    }

    public void setNum_rounds(int num_rounds) {
        this.num_rounds = num_rounds;
    }
}
