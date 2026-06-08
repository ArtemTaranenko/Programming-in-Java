package Model;

import java.util.Objects;

public class Team
{
    private String name;
    private int league;

    public Team(String name, int league) {
        this.name = name;
        this.league = league;
    }

    public String getName() {
        return name;
    }

    public int getLeague() {
        return league;
    }

    @Override
    public String toString() {
        return " team: " +
                 name + " "
                + league +
                " league";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Team team)) return false;
        return league == team.league && Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, league);
    }
}
