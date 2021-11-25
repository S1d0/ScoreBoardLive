package org.football.world.cup.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FootballScoreBoard implements ScoreBoard {
    private final List<Game> games = new ArrayList<>();

    @Override
    public Game startGame(final String homeTeam, final String awayTeam, final LocalDateTime start) {
        Game startedGame = new Game(homeTeam, awayTeam, start);
        addGame(startedGame);
        return startedGame;
    }

    private void addGame(final Game startedGame) {
        games.add(startedGame);
    }

    @Override
    public List<Game> getActiveGames() {
        return games;
    }

    @Override
    public void finishGame(final Game activeGame) {
        games.remove(activeGame);
    }

    @Override
    public Game getGame(final Game activeGame) {
        return games.stream()
                .filter(game -> game.equals(activeGame))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public void updateGameScore(final Game searchGame, final Score score) {
        games.stream()
                .filter(game -> game.equals(searchGame))
                .findFirst()
                .ifPresent(game -> game.updateScore(score));
    }

    @Override
    public List<Game> getGamesSummary() {
        return games.stream()
                .sorted(
                        Comparator.comparing(Game::getGameTotalScores)
                                .thenComparing(Game::getStartDateTime).reversed()
                )
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Game getGameByTeams(final String homeTeam, final String awayTeam) {
        return games.stream()
                .filter(game -> game.getHomeTeamName().equals(homeTeam) && game.getAwayTeamName().equals(awayTeam))
                .findFirst()
                .orElseThrow();
    }
}
