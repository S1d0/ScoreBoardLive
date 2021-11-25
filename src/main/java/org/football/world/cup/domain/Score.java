package org.football.world.cup.domain;

public class Score {
    private final int homeScore;
    private final int awayScore;

    public Score() {
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public static Score of(int homeScore, int awayScore) {
        return new Score(homeScore, awayScore);
    }

    private Score(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public int getTotal() {
        return homeScore + awayScore;
    }
}
