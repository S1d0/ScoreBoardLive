package org.football.world.cup.domain;

import java.time.LocalDateTime;

public class Game {
    private final Team home;
    private final Team away;
    private final LocalDateTime startDateTime;
    private Score score;

    public Game(String home, String away, LocalDateTime begins) {
        this.startDateTime = begins;
        this.home = new FootballTeam(home);
        this.away = new FootballTeam(away);
        this.score = new Score();
    }

    public Team getHomeTeam() {
        return home;
    }

    public Team getAwayTeam() {
        return away;
    }

    public int getHomeScore() {
        return this.score.getHomeScore();
    }

    public int getAwayScore() {
        return score.getAwayScore();
    }

    public int getGameTotalScores() {
        return score.getTotal();
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public String getAwayTeamName() {
        return away.getTeamName();
    }

    public String getHomeTeamName() {
        return home.getTeamName();
    }

    public void updateScore(Score score) {
        this.score = score;
    }
}
