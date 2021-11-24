package org.football.world.cup.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;

public class Game {
    private final FootballTeam home;
    private final FootballTeam away;
    private final LocalDateTime startDateTime;

    private int homeScore;
    private int awayScore;

    public Game(FootballTeam home, FootballTeam away, LocalDateTime begins) {
        this.startDateTime = begins;
        this.home = home;
        this.away = away;

        this.awayScore = 0;
        this.homeScore = 0;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public boolean isActive() {
        return false;
    }

    public FootballTeam getHomeTeam() {
        return home;
    }

    public FootballTeam getAwayTeam() {
        return away;
    }

    public int getHomeScore(){
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public int getGameTotalScores() {
        return homeScore + awayScore;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }
}
