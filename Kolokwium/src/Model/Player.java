package Model;

import java.time.LocalDate;

public class Player extends Person
{
    private String position;
    private int n_goals;

    public Player(String name, LocalDate dob, String teamName, int league, String function, String position, int n_goals) {
        super(name, dob, teamName, league, function);
        this.position = position;
        this.n_goals = n_goals;
    }

    public void updateGoalsForFirstLeague(int goals)
    {
        if (team.getLeague() == 1 && position.equals("forward")) {
            this.n_goals += goals;
            System.out.println("-> " + function + ": " + name + " przed: " + (n_goals-goals) + " po: " + n_goals);
        }
    }
    @Override
    public String toString() {
        return super.toString() +
                " position: " + position + " " +
                n_goals + " goals";
    }

    public String getPosition() {
        return position;
    }

    public int getN_goals() {
        return n_goals;
    }
}
