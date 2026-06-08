package Model;

import java.time.LocalDate;
import java.util.Date;

public class Person
{
    protected String name;
    protected LocalDate dob;
    protected Team team;
    protected String function;

    public Person(String name, LocalDate dob, String teamName, int league, String function) {
        this.name = name;
        this.dob = dob;
        this.team = new Team(teamName, league);
        this.function = function;
    }

    @Override
    public String toString() {
        return function + ": " +
                name +
                " dob: " +
                dob.toString() +
                team.toString();
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Team getTeam() {
        return team;
    }

    public String getFunction() {
        return function;
    }
}
