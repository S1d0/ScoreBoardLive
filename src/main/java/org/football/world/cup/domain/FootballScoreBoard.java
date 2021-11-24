package org.football.world.cup.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FootballScoreBoard implements ScoreBoard{
    private final List<Game> games = new ArrayList<>();

    @Override
    public Game startGame(String homeTeam, String awayTeam) {
        return null;
    }

    @Override
    public List<Game> getActiveGames() {
        return null;
    }

    @Override
    public Game finishGame(Game activeGame) {
        return null;
    }

    @Override
    public Game getGame(Game activeGame) {
        return null;
    }

    @Override
    public void updateGameScore(Game activeGame, Score score) {

    }

    @Override
    public List<Game> getGamesSummary() {
        return games.stream().filter(Game::isActive)
                .sorted(
                        Comparator.comparing(Game::getGameTotalScores)
                                .thenComparing(Game::getStartDateTime))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Game getGameByTeams(String homeTeam, String awayTeam) {
        return null;
    }
}
