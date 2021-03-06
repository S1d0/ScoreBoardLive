package org.football.world.cup.domain;

public class FootballTeam implements Team {
    private final String teamName;

    public FootballTeam(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String getTeamName() {
        return teamName;
    }
}
